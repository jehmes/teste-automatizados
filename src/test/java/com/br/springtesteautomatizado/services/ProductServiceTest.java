package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.enums.ProductsErrorsEnum;
import com.br.springtesteautomatizado.exceptions.DuplicateProductExcpetion;
import com.br.springtesteautomatizado.exceptions.ProductException;
import com.br.springtesteautomatizado.models.Cart;
import com.br.springtesteautomatizado.models.Product;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.models.User;
import com.br.springtesteautomatizado.repositories.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Autowired
    private ProductServiceImp productService;
    @MockBean
    private ProductRepository productRepository;
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private Sale sale;
    private List<Product> productList;

    @Before
    public void setup() {
        User user = new User();
        user.setNome("Thales");
        user.setIdade(26);
        user.setCpf("11278342400");

        productList = Arrays.asList(
                new Product(1L, "Sapato", BigDecimal.valueOf(199.90), 5),
                new Product(2L, "Camisa", BigDecimal.valueOf(69.90), 2));

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProducts(productList);
        cart.setAmount(BigDecimal.valueOf(269.8));

        sale = new Sale();
        sale.setUser(user);
        sale.setAmount(BigDecimal.valueOf(269.8));
        sale.setDateTime(LocalDateTime.now());

        when(productRepository.findByNames(productList)).thenReturn(Optional.empty());
    }

    @Test
    public void subtractProducts_Void() throws Exception {
        //Arrange
        Mockito.when(productRepository.findById(productList.get(0).getId())).thenReturn(Optional.of(productList.get(0)));
        Mockito.when(productRepository.findById(productList.get(1).getId())).thenReturn(Optional.of(productList.get(1)));

        //Action
        productService.subtractProducts(productList);

        //Assert
        verify(productRepository, Mockito.times(1)).saveAll(Mockito.anyCollection());
        long milliSecondsValue = milliSecondsBetweenDate(sale.getDateTime(), LocalDateTime.now());
        Assert.assertTrue(milliSecondsValue < 10.000);
    }


    @Test
    public void subtractProducts_throwProductExcpetion_negativeStock() {
        //Arrange
        Product productNegativeStock = new Product(2L, "Camisa", BigDecimal.valueOf(69.90), 1);
        Mockito.when(productRepository.findById(productList.get(0).getId())).thenReturn(Optional.of(productList.get(0)));
        Mockito.when(productRepository.findById(productList.get(1).getId())).thenReturn(Optional.of(productNegativeStock));

        try {
            //Action
            productService.subtractProducts(productList);
            Assert.fail("Esperava lançar exception de estoque negativo");
        } catch (ProductException e) {
            //Assert
            Assert.assertEquals(ProductsErrorsEnum.ERROR_NEGATIVE_STOCK.getName() , e.getMessage());
        }
    }

    @Test
    public void subtractProducts_setQuantity0WhenProductFromDBIs0() throws ProductException {
        //Arrange
        Product dbProductQuantity2 = new Product(2L, "Camisa", BigDecimal.valueOf(69.90), 4);
        Mockito.when(productRepository.findById(productList.get(0).getId())).thenReturn(Optional.of(productList.get(0)));
        Mockito.when(productRepository.findById(productList.get(1).getId())).thenReturn(Optional.of(dbProductQuantity2));

        //Action
        productService.subtractProducts(productList);

        //Assert
        Assert.assertEquals(0L, productList.get(0).getQuantity().longValue());
//        Assert.assertEquals(0L, productList.get(1).getQuantity().longValue());
    }

    @Test
    public void saveProductList() throws DuplicateProductExcpetion {
        productService.saveProductList(productList);

        verify(productRepository).saveAll(productList);
    }

    @Test
    public void throwsADuplicateProductExceptionWhenSaveAListOfProductsWithSomeDuplicateProduct() throws DuplicateProductExcpetion {
        exception.expect(DuplicateProductExcpetion.class);

        when(productRepository.findByNames(productList)).thenReturn(Optional.ofNullable(productList.get(0)));

        productService.saveProductList(productList);

        verify(productRepository, never()).saveAll(productList);
    }

    private long milliSecondsBetweenDate(LocalDateTime date1, LocalDateTime date2) {
        Duration duration = Duration.between(date1, date2);
        return Math.abs(duration.toMillis());
    }
}
