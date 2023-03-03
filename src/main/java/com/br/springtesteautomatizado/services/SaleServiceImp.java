package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.enums.ProductsErrorsEnum;
import com.br.springtesteautomatizado.enums.UserErrorsEnum;
import com.br.springtesteautomatizado.exceptions.ProductException;
import com.br.springtesteautomatizado.exceptions.UserException;
import com.br.springtesteautomatizado.factorys.CardPaymentFactory;
import com.br.springtesteautomatizado.factorys.PaymentFactory;
import com.br.springtesteautomatizado.factorys.PixPaymentFactory;
import com.br.springtesteautomatizado.interfaces.IPaymentService;
import com.br.springtesteautomatizado.interfaces.IProductService;
import com.br.springtesteautomatizado.interfaces.ISaleService;
import com.br.springtesteautomatizado.models.*;
import com.br.springtesteautomatizado.repositories.ProductRepository;
import com.br.springtesteautomatizado.repositories.SaleRepository;
import com.br.springtesteautomatizado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImp implements ISaleService {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private IProductService productService;
    @Autowired
    private IPaymentService paymentService;

    @Override
    public PaymentProof saveSale(Sale sale) throws Exception {
        saleRepository.save(sale);
        productService.subtractProducts(sale.getProductList());
        return paymentService.doPayment(sale);
    }

}
