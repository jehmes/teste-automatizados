package com.br.springtesteautomatizado.interfaces;

import com.br.springtesteautomatizado.models.Cart;

public interface ISaleService {
    void saveSale(Cart cart) throws Exception;
}
