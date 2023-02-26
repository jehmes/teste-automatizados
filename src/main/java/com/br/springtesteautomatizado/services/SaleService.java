package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.interfaces.ISaleService;
import com.br.springtesteautomatizado.models.Cart;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SaleService implements ISaleService {

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    ProductService productService;

    @Override
    public void saveSale(Cart cart) throws Exception {
        Sale sale = new Sale(cart, cart.getUser(), LocalDate.now(), cart.getTotalPrice());
        saleRepository.save(sale);
        productService.subtractProducts(cart.getProducts());
    }

}
