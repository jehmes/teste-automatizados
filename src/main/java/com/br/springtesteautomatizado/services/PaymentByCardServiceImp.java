package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.enums.PaymentErrorsEnum;
import com.br.springtesteautomatizado.enums.PaymentMethodsEnum;
import com.br.springtesteautomatizado.exceptions.PaymentException;
import com.br.springtesteautomatizado.models.Payment;
import com.br.springtesteautomatizado.models.PaymentProof;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.repositories.PaymentProofRepository;
import com.br.springtesteautomatizado.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentByCardServiceImp {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentProofRepository paymentProofRepository;
    public PaymentByCardServiceImp() {
    }

    public PaymentProof payment(Sale sale) throws PaymentException {
        String numberCard = "112233445566";

        if (!authenticatedCard(numberCard)) {
            throw new PaymentException(PaymentErrorsEnum.CARD_INVALID.getName());
        }

        Payment newPayment = new Payment(null, sale.getDateTime(), PaymentMethodsEnum.CARD, sale.getAmount());
        paymentRepository.save(newPayment);

        return generatePaymentProof(sale);
    }

    private PaymentProof generatePaymentProof(Sale sale) {
        PaymentProof paymentProof = new PaymentProof(null, sale.getDateTime(), sale.getUser(), sale.getAmount(),
                sale.getPayment().getPaymentMethod(), sale.getProductList().stream().toList());

        paymentProofRepository.save(paymentProof);

        return paymentProof;
    }

    private boolean authenticatedCard(String numberCard) {
        return (numberCard.equals("112233445566"));
    }
}
