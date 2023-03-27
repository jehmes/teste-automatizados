package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.enums.PaymentMethodsEnum;
import com.br.springtesteautomatizado.enums.ProductsErrorsEnum;
import com.br.springtesteautomatizado.enums.UserErrorsEnum;
import com.br.springtesteautomatizado.exceptions.ProductException;
import com.br.springtesteautomatizado.exceptions.UserException;
import com.br.springtesteautomatizado.models.*;
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
import java.time.LocalDateTime;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SaleServiceTest {

    @MockBean
    private ProductServiceImp productService;
    @MockBean
    private SaleRepository saleRepository;
    @Autowired
    private SaleServiceImp saleServiceImp;
    private Sale sale;

    @Before
    public void setup() {
        User user = new User();
        user.setNome("Thales");
        user.setIdade(26);
        user.setCpf("11278342400");

        List<Product> productList = Arrays.asList(
                new Product(1L, "Sapato", BigDecimal.valueOf(199.90), 5),
                new Product(2L, "Camisa", BigDecimal.valueOf(69.90), 2));

        Payment payment = new CreditCardPayment(LocalDate.now(), PaymentMethodsEnum.CARD,
                new BigDecimal("269.8"), "1234567890123456", "123",
                "Jehmes", LocalDate.now().plusDays(1));

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProducts(productList);
        cart.setAmount(BigDecimal.valueOf(269.8));

        sale = new Sale();
        sale.setUser(user);
        sale.setAmount(BigDecimal.valueOf(269.8));
        sale.setDateTime(LocalDateTime.now());

        sale.setProductList(productList);
        sale.setPayment(payment);

    }

    @Test
    public void doASaleDoSave() throws Exception {
        //Action
        saleServiceImp.saveSale(sale);

        //Assert
        Mockito.verify(saleRepository, Mockito.times(1)).save(sale);
        Mockito.verify(productService, Mockito.times(1)).subtractProducts(Mockito.anyList());
    }

}
