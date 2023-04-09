package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.enums.PaymentErrorsEnum;
import com.br.springtesteautomatizado.exceptions.PaymentInvalidException;
import com.br.springtesteautomatizado.interfaces.IPaymentService;
import com.br.springtesteautomatizado.models.Payment;
import com.br.springtesteautomatizado.models.PixPayment;
import com.br.springtesteautomatizado.models.Sale;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("pixPaymentService")
public class PaymentByPixServiceImp implements IPaymentService {

    public PaymentByPixServiceImp() {
    }

    @Override
    public Payment doPayment(Sale sale) throws PaymentInvalidException {
        PixPayment payment = (PixPayment) sale.getPayment();

        if (!authenticatedPix(payment.getPixKey())) {
            throw new PaymentInvalidException(PaymentErrorsEnum.PIX_INVALID.getName());
        }

        System.out.println("Pagamento debitado da conta!");

        return payment;
    }

    private boolean authenticatedPix(String pixKey) {
        return (pixKey.equals("99887766"));
    }
}
