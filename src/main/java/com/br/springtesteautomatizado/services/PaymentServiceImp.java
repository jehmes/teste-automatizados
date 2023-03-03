package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.exceptions.PaymentException;
import com.br.springtesteautomatizado.factorys.CardPaymentFactory;
import com.br.springtesteautomatizado.factorys.PaymentFactory;
import com.br.springtesteautomatizado.factorys.PixPaymentFactory;
import com.br.springtesteautomatizado.interfaces.IPaymentService;
import com.br.springtesteautomatizado.models.PaymentProof;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.repositories.PaymentProofRepository;
import com.br.springtesteautomatizado.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImp implements IPaymentService {

    @Autowired
    private PaymentByCardServiceImp paymentByCardServiceImp;
    @Autowired
    private PaymentByPixServiceImp paymentByPixServiceImp;

    @Override
    public PaymentProof doPayment(Sale sale) throws PaymentException {
        PaymentFactory paymentFactory;
        switch (sale.getPayment().getPaymentMethod()) {
            case CARD:
                paymentFactory = new CardPaymentFactory(paymentByCardServiceImp);
                break;
            case PIX:
                paymentFactory = new PixPaymentFactory(paymentByPixServiceImp);
                break;
            default:
                throw new RuntimeException("Método de pagamento inválido");
        }
        return paymentFactory.createPayment(sale);
    }
}
