package com.br.springtesteautomatizado.interfaces;

import com.br.springtesteautomatizado.exceptions.PaymentException;
import com.br.springtesteautomatizado.models.Payment;
import com.br.springtesteautomatizado.models.PaymentProof;
import com.br.springtesteautomatizado.models.Sale;

public interface IPaymentService {

    PaymentProof doPayment(Sale sale) throws PaymentException;
}
