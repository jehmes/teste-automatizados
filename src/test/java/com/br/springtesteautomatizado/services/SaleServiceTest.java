package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.enums.ProductsErrorsEnum;
import com.br.springtesteautomatizado.enums.UserErrorsEnum;
import com.br.springtesteautomatizado.exceptions.ProductException;
import com.br.springtesteautomatizado.exceptions.UserException;
import com.br.springtesteautomatizado.models.Cart;
import com.br.springtesteautomatizado.models.Product;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.models.User;
import com.br.springtesteautomatizado.repositories.ProductRepository;
import com.br.springtesteautomatizado.repositories.SaleRepository;
import com.br.springtesteautomatizado.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleServiceTest {

    @MockBean
    private ProductServiceImp productService;
    @MockBean
    private SaleRepository saleRepository;
    @MockBean
    private ProductRepository productRepository;
    @Autowired
    private SaleServiceImp saleServiceImp;
    @MockBean
    private UserRepository userRepository;
    User user;
    private Sale sale;
    private List<Product> productList;

    @Before
    public void setup() {
        user = new User();
        user.setId(1L);
        user.setNome("Thales");
        user.setIdade(26);
        user.setCpf("11278342400");

        productList = Arrays.asList(
                new Product(1L, "Sapato", BigDecimal.valueOf(199.90), 5),
                new Product(2L, "Camisa", BigDecimal.valueOf(69.90), 2));

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setUser(user);
        cart.setProducts(productList);
        cart.setAmount(BigDecimal.valueOf(269.8));

        sale = new Sale();
        sale.setId(1L);
        sale.setUser(user);
        sale.setAmount(BigDecimal.valueOf(269.8));
        sale.setDateTime(LocalDate.now());

        sale.setProductList(productList);
    }

    @Test
    public void saveSaleTest_Void() throws Exception {
        //Arrange
        Sale sale = Mockito.mock(Sale.class);
        List<Product> productList = Collections.singletonList(Mockito.mock(Product.class));

        //Action
        saleRepository.save(sale);
        productService.subtractProducts(productList);

        //Assert
        Mockito.verify(saleRepository, Mockito.times(1)).save(Mockito.any(Sale.class));
        Mockito.verify(productService, Mockito.times(1)).subtractProducts(Mockito.anyList());

    }

    @Test
    public void saveSaleTest_throwProductExcpetion_findProduct() throws Exception {
        //Arrange
        User userMock = Mockito.mock(User.class);
        Mockito.when(userMock.getNome()).thenReturn("Thales");
        Mockito.when(userMock.getId()).thenReturn(1L);

        Product product1 = Mockito.mock(Product.class);
        Mockito.when(product1.getId()).thenReturn(1L);
        Mockito.when(product1.getName()).thenReturn("Sapato");
        Mockito.when(product1.getPrice()).thenReturn(BigDecimal.valueOf(199.90));
        Mockito.when(product1.getQuantity()).thenReturn(1);

        Product product2 = Mockito.mock(Product.class);
        Mockito.when(product2.getId()).thenReturn(2L);
        Mockito.when(product2.getName()).thenReturn("Camisa");
        Mockito.when(product2.getPrice()).thenReturn(BigDecimal.valueOf(69.90));
        Mockito.when(product2.getQuantity()).thenReturn(2);

        Mockito.when(userRepository.findById(userMock.getId())).thenReturn(Optional.of(userMock));
        Mockito.when(productRepository.findById(product1.getId())).thenReturn(Optional.of(product1));
//        Mockito.when(productRepository.findById(product2.getId())).thenReturn(Optional.of(product2));

        //Action
        try {
            saleServiceImp.saveSale(sale);
            Assert.fail("Esperava lançar exception de não encontrar o produto");
        } catch (ProductException e) {
            //Assert
            Assert.assertEquals(ProductsErrorsEnum.ERROR_FIND_PRODUCT.getName(), e.getMessage());
        }
    }

    @Test
    public void saveSale_ThrowUserException() throws Exception {
        //Arrange
        Mockito.when(userRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());
//        Mockito.when(saleServiceImp.userRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(Mockito.mock(User.class)));

        //Action
        try {
            saleServiceImp.saveSale(sale);
            Assert.fail("Esperava lançar exceção de usuario nao encontrado");
        } catch (UserException e) {
            //Assert
            Assert.assertEquals(UserErrorsEnum.ERROR_FIND_USER.getName(), e.getMessage());
        } catch (ProductException e) {
            Assert.fail("Esperava lançar exceção de usuario nao encontrado");
        }


    }

}
