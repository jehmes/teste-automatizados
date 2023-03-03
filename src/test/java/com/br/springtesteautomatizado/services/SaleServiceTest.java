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
    private User user;
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

}
