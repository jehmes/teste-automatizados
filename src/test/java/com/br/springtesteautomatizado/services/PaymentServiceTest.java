package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.enums.PaymentMethodsEnum;
import com.br.springtesteautomatizado.exceptions.PaymentException;
import com.br.springtesteautomatizado.models.*;
import com.br.springtesteautomatizado.repositories.PaymentProofRepository;
import com.br.springtesteautomatizado.repositories.PaymentRepository;
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
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PaymentServiceTest {
    private User user;
    private Sale sale;
    private List<Product> productList;
    private Payment payment;
    private PaymentProof paymentProof;
    @Autowired
    private PaymentServiceImp paymentServiceImp;
    @MockBean
    private PaymentRepository paymentRepository;
    @MockBean
    private PaymentProofRepository paymentProofRepository;
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

        payment = new Payment(null, LocalDate.now(), PaymentMethodsEnum.CARD, new BigDecimal("269.8"));

        sale.setPayment(payment);

        paymentProof = new PaymentProof(null, LocalDate.now(), user, new BigDecimal("269.8"), PaymentMethodsEnum.CARD, productList);
    }
    @Test
    public void doPayment_doPaymentAndReturnPaymentProof () throws PaymentException {
        Mockito.when(paymentRepository.save(Mockito.any(Payment.class))).thenReturn(payment);
        Mockito.when(paymentProofRepository.save(Mockito.any(PaymentProof.class))).thenReturn(paymentProof);

//        sale.setAmount(new BigDecimal("10"));
        PaymentProof paymentProofResult = paymentServiceImp.doPayment(sale);

        Assert.assertEquals(paymentProofResult, paymentProof);
    }

    @Test
    public void doPayment_RuntimeExceptionInvalidPaymentMethod() {
        Mockito.when(paymentRepository.save(Mockito.any(Payment.class))).thenReturn(payment);
        Mockito.when(paymentProofRepository.save(Mockito.any(PaymentProof.class))).thenReturn(paymentProof);

        sale.getPayment().setPaymentMethod(PaymentMethodsEnum.INVALID_PAYMENT_METHOD);
        try {
            PaymentProof paymentProofResult = paymentServiceImp.doPayment(sale);
            Assert.fail("Esperava lançar RuntimeExcpetion de metodo de pagamento inválido");
        } catch (RuntimeException e) {
            Assert.assertEquals(e.getMessage(), "Método de pagamento inválido");
        } catch (PaymentException e) {
            Assert.fail("Esperava lançar RuntimeExcpetion de metodo de pagamento inválido");
        }

    }
}
