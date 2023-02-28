package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.exceptions.ProductException;
import com.br.springtesteautomatizado.interfaces.ISaleService;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImp implements ISaleService {

    @Autowired
    SaleRepository saleRepository;

    @Autowired
    ProductServiceImp productService;

    @Override
    public void saveSale(Sale sale) throws ProductException {
        saleRepository.save(sale);
        productService.subtractProducts(sale.getCart().getProducts());
    }

}
