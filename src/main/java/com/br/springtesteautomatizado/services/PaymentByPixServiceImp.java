package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.enums.PaymentErrorsEnum;
import com.br.springtesteautomatizado.enums.PaymentMethodsEnum;
import com.br.springtesteautomatizado.exceptions.PaymentException;
import com.br.springtesteautomatizado.models.Payment;
import com.br.springtesteautomatizado.models.PaymentProof;
import com.br.springtesteautomatizado.models.Product;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.repositories.PaymentProofRepository;
import com.br.springtesteautomatizado.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentByPixServiceImp{

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentProofRepository paymentProofRepository;
    public PaymentByPixServiceImp() {
    }

    public PaymentProof payment(Sale sale) throws PaymentException {
        String pixKey = "99887766";
        String bank = "brasil";

        if (!authenticatedPix(pixKey, bank)) {
            throw new PaymentException(PaymentErrorsEnum.PIX_INVALID.getName());
        }

        Payment newPayment = new Payment(null, sale.getDateTime(), PaymentMethodsEnum.PIX, sale.getAmount());
        paymentRepository.save(newPayment);

        return generatePaymentProof(sale);
    }

    private PaymentProof generatePaymentProof(Sale sale) {
        PaymentProof paymentProof = new PaymentProof(null, sale.getDateTime(), sale.getUser(), sale.getAmount(),
                sale.getPayment().getPaymentMethod(), sale.getProductList().stream().toList());

        paymentProofRepository.save(paymentProof);

        return paymentProof;
    }

    private boolean authenticatedPix(String pixKey, String bank) {
        return (pixKey.equals("99887766") && bank.equals("brasil"));
    }
}
