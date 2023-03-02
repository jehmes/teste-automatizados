package com.br.springtesteautomatizado.interfaces;

import com.br.springtesteautomatizado.models.PaymentProof;
import com.br.springtesteautomatizado.models.Sale;

public interface ISaleService {
    PaymentProof saveSale(Sale sale) throws Exception;
}
