package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.enums.PaymentErrorsEnum;
import com.br.springtesteautomatizado.enums.PaymentMethodsEnum;
import com.br.springtesteautomatizado.exceptions.PaymentException;
import com.br.springtesteautomatizado.interfaces.IPaymentService;
import com.br.springtesteautomatizado.models.Payment;
import com.br.springtesteautomatizado.models.PaymentProof;
import com.br.springtesteautomatizado.models.PixPayment;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.repositories.PaymentProofRepository;
import com.br.springtesteautomatizado.repositories.PixPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Qualifier("pixPaymentService")
public class PaymentByPixServiceImp implements IPaymentService {

    public PaymentByPixServiceImp() {
    }

    @Override
    public Payment doPayment(Sale sale) throws PaymentException {
        PixPayment payment = (PixPayment) sale.getPayment();

        if (!authenticatedPix(payment.getPixKey())) {
            throw new PaymentException(PaymentErrorsEnum.PIX_INVALID.getName());
        }

        System.out.println("Pagamento debitado da conta!");

        return payment;
    }

    private boolean authenticatedPix(String pixKey) {
        return (pixKey.equals("99887766"));
    }
}
