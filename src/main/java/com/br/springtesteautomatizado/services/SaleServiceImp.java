package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.factorys.PaymentFactory;
import com.br.springtesteautomatizado.interfaces.IProductService;
import com.br.springtesteautomatizado.interfaces.ISaleService;
import com.br.springtesteautomatizado.models.Payment;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImp implements ISaleService {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private IProductService productService;

    @Override
    public Payment saveSale(Sale sale) throws Exception {
        Payment payment = PaymentFactory.createPaymentType(sale);
        productService.subtractProducts(sale.getProductList());
        saleRepository.save(sale);
        return payment;
    }

}
