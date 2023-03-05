package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.enums.PaymentErrorsEnum;
import com.br.springtesteautomatizado.enums.PaymentMethodsEnum;
import com.br.springtesteautomatizado.exceptions.PaymentException;
import com.br.springtesteautomatizado.interfaces.IPaymentService;
import com.br.springtesteautomatizado.models.CreditCardPayment;
import com.br.springtesteautomatizado.models.Payment;
import com.br.springtesteautomatizado.models.PaymentProof;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.repositories.CreditCardPaymentRepository;
import com.br.springtesteautomatizado.repositories.PaymentProofRepository;
import com.br.springtesteautomatizado.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("cardPaymentService")
public class PaymentByCardServiceImp implements IPaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentProofRepository paymentProofRepository;
    @Autowired
    private CreditCardPaymentRepository creditCardPaymentRepository;
    public PaymentByCardServiceImp() {
    }

    @Override
    public Payment doPayment(Sale sale) throws PaymentException {
        CreditCardPayment payment = (CreditCardPayment) sale.getPayment();
        if (!authenticatedCard(payment.getCardNumber())) {
            throw new PaymentException(PaymentErrorsEnum.CARD_INVALID.getName());
        }

        System.out.println("Aplicado pagamento na fatura do cartão!");

        return payment;
    }

    private PaymentProof generatePaymentProof(Sale sale) {
        PaymentProof paymentProof = new PaymentProof(null, sale.getDateTime(), sale.getUser(), sale.getAmount(),
                PaymentMethodsEnum.CARD, sale.getProductList().stream().toList());

        paymentProofRepository.save(paymentProof);

        return paymentProof;
    }

    private boolean authenticatedCard(String numberCard) {
        return (numberCard.equals("1234567890123456"));
    }
}
