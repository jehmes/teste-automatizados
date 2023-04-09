package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.enums.PaymentMethodsEnum;
import com.br.springtesteautomatizado.models.*;
import com.br.springtesteautomatizado.repositories.SaleRepository;
import com.br.springtesteautomatizado.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class SaleServiceImpTests {

    @MockBean
    private ProductServiceImp productService;
    @MockBean
    private SaleRepository saleRepository;
    @Autowired
    private SaleServiceImp saleServiceImp;
    @MockBean
    private UserRepository userRepository;
    private Sale sale;
    User user = new User();
    @BeforeEach
    public void setup() {
        user.setId(1L);
        user.setNome("Thales");
        user.setIdade(26);
        user.setCpf("11278342400");

        List<Product> productList = Arrays.asList(
                new Product("Sapato", BigDecimal.valueOf(199.90), 5),
                new Product("Camisa", BigDecimal.valueOf(69.90), 2));

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
    public void testDoASaleDoSave() throws Exception {
        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));

        //Action
        saleServiceImp.saveSale(sale);

        //Assert
        Mockito.verify(saleRepository, Mockito.times(1)).save(sale);
        Mockito.verify(productService, Mockito.times(1)).subtractProducts(Mockito.anyList());
    }

}
