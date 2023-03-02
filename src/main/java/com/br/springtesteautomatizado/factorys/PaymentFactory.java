package com.br.springtesteautomatizado.factorys;

import com.br.springtesteautomatizado.exceptions.PaymentException;
import com.br.springtesteautomatizado.models.PaymentProof;
import com.br.springtesteautomatizado.models.Sale;

public abstract class PaymentFactory {
    public abstract PaymentProof createPayment(Sale sale) throws PaymentException;
}

