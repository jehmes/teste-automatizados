package com.br.springtesteautomatizado.services;


import com.br.springtesteautomatizado.enums.PaymentErrorsEnum;
import com.br.springtesteautomatizado.enums.PaymentMethodsEnum;
import com.br.springtesteautomatizado.exceptions.PaymentException;
import com.br.springtesteautomatizado.models.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PaymentByCardServiceTest {

    @Autowired
    private PaymentByCardServiceImp paymentByCardServiceImp;
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

        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProducts(productList);
        cart.setAmount(BigDecimal.valueOf(269.8));

        sale = new Sale();
        sale.setUser(user);
        sale.setAmount(BigDecimal.valueOf(269.8));
        sale.setDateTime(LocalDateTime.now());

        sale.setProductList(productList);

        CreditCardPayment payment = new CreditCardPayment(LocalDate.now(), PaymentMethodsEnum.CARD, new BigDecimal("269.8"), "1234567890123456", "123", "Jehmes", LocalDate.now().plusDays(1));

        sale.setPayment(payment);

    }

    @Test
    public void doPaymentWithValidCard() throws PaymentException {

        Payment paymentResult = paymentByCardServiceImp.doPayment(sale);
//        sale.setPayment(payment = new CreditCardPayment(null, LocalDate.now(), PaymentMethodsEnum.CARD, new BigDecimal("249.8"), "1234567890123456", "123", "Jehmes", LocalDate.now().plusDays(1)));
        Assert.assertTrue(paymentResult instanceof CreditCardPayment);
        Assert.assertNotNull(paymentResult);
    }

    @Test
    public void throwAPaymentExceptionWhenUseInvalidCard() {
        ((CreditCardPayment) sale.getPayment()).setCardNumber("123");
        try {
            paymentByCardServiceImp.doPayment(sale);
            Assert.fail("Deveria lançar execção de cartão inválido!");
        } catch (PaymentException e) {
            Assert.assertEquals(e.getMessage(), PaymentErrorsEnum.CARD_INVALID.getName());
        }
    }

}
