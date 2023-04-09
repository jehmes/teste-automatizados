package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.enums.PaymentErrorsEnum;
import com.br.springtesteautomatizado.exceptions.PaymentInvalidException;
import com.br.springtesteautomatizado.interfaces.IPaymentService;
import com.br.springtesteautomatizado.models.CreditCardPayment;
import com.br.springtesteautomatizado.models.Payment;
import com.br.springtesteautomatizado.models.Sale;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("cardPaymentService")
public class PaymentByCardServiceImp implements IPaymentService {

    public PaymentByCardServiceImp() {
    }

    @Override
    public Payment doPayment(Sale sale) throws PaymentInvalidException {
        CreditCardPayment payment = (CreditCardPayment) sale.getPayment();
        if (!authenticatedCard(payment.getCardNumber())) {
            throw new PaymentInvalidException(PaymentErrorsEnum.CARD_INVALID.getName());
        }

        System.out.println("Aplicado pagamento na fatura do cartão!");

        return payment;
    }

    private boolean authenticatedCard(String numberCard) {
        return (numberCard.equals("1234567890123456"));
    }
}
