package com.br.springtesteautomatizado.interfaces;

import com.br.springtesteautomatizado.exceptions.PaymentException;
import com.br.springtesteautomatizado.models.Payment;
import com.br.springtesteautomatizado.models.Sale;

public interface IPaymentService {

    Payment doPayment(Sale sale) throws PaymentException;
}
