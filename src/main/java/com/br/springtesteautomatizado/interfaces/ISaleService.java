package com.br.springtesteautomatizado.interfaces;

import com.br.springtesteautomatizado.models.Payment;
import com.br.springtesteautomatizado.models.Sale;

public interface ISaleService {
    Payment saveSale(Sale sale) throws Exception;
}
