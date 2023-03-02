package com.br.springtesteautomatizado.factorys;

import com.br.springtesteautomatizado.exceptions.PaymentException;
import com.br.springtesteautomatizado.models.PaymentProof;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.services.PaymentByCardServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

public class CardPaymentFactory extends PaymentFactory {
    private final PaymentByCardServiceImp paymentByCardService;
    public CardPaymentFactory(PaymentByCardServiceImp paymentByCardService) {
        this.paymentByCardService = paymentByCardService;
    }
    @Override
    public PaymentProof createPayment(Sale sale) throws PaymentException {
        return paymentByCardService.payment(sale);
    }
}
