package com.br.springtesteautomatizado.factorys;

import com.br.springtesteautomatizado.exceptions.PaymentException;
import com.br.springtesteautomatizado.models.PaymentProof;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.services.PaymentByPixServiceImp;

public class PixPaymentFactory extends PaymentFactory {
    private final PaymentByPixServiceImp paymentByPixServiceImp;
    public PixPaymentFactory(PaymentByPixServiceImp paymentByPixServiceImp) {
        this.paymentByPixServiceImp = paymentByPixServiceImp;
    }
    @Override
    public PaymentProof createPayment(Sale sale) throws PaymentException {
        return paymentByPixServiceImp.payment(sale);
    }
}
