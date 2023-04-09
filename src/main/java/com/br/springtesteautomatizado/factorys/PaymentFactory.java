package com.br.springtesteautomatizado.factorys;

import com.br.springtesteautomatizado.enums.PaymentMethodsEnum;
import com.br.springtesteautomatizado.exceptions.PaymentInvalidException;
import com.br.springtesteautomatizado.models.Payment;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.services.PaymentByCardServiceImp;
import com.br.springtesteautomatizado.services.PaymentByPixServiceImp;

public class PaymentFactory {

    public static Payment createPaymentType (Sale sale) throws PaymentInvalidException {
        Payment payment;
        if (sale.getPayment().getPaymentMethod().equals(PaymentMethodsEnum.PIX)) {
            payment = new PaymentByPixServiceImp().doPayment(sale);
        } else if (sale.getPayment().getPaymentMethod().equals(PaymentMethodsEnum.CARD)){
            payment = new PaymentByCardServiceImp().doPayment(sale);
        } else {
            throw new PaymentInvalidException(PaymentMethodsEnum.INVALID_PAYMENT_METHOD.getName());
        }
        return payment;
    }
}
