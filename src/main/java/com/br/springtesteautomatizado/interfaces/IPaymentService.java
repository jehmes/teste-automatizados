package com.br.springtesteautomatizado.interfaces;

import com.br.springtesteautomatizado.models.PaymentProof;
import com.br.springtesteautomatizado.models.Sale;

public interface IPaymentService {

    PaymentProof payment(Sale sale);
}
