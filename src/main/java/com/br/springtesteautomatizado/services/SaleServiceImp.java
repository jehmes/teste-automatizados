package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.enums.ProductsErrorsEnum;
import com.br.springtesteautomatizado.enums.UserErrorsEnum;
import com.br.springtesteautomatizado.exceptions.ProductException;
import com.br.springtesteautomatizado.exceptions.UserException;
import com.br.springtesteautomatizado.factorys.CardPaymentFactory;
import com.br.springtesteautomatizado.factorys.PaymentFactory;
import com.br.springtesteautomatizado.factorys.PixPaymentFactory;
import com.br.springtesteautomatizado.interfaces.IPaymentService;
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
    private ProductRepository productRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private  ProductServiceImp productService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentByCardServiceImp paymentByCardServiceImp;
    @Autowired
    private PaymentByPixServiceImp paymentByPixServiceImp;

    @Override
    public PaymentProof saveSale(Sale sale) throws Exception {
        User user = userRepository.findById(sale.getUser().getId())
                .orElseThrow(() -> new UserException(UserErrorsEnum.ERROR_FIND_USER.getName()));
        sale.setUser(user);

        for (Product product : sale.getProductList()) {
            productRepository.findById(product.getId()).orElseThrow(
                    () -> new ProductException(ProductsErrorsEnum.ERROR_FIND_PRODUCT.getName())
            );
        }
        PaymentFactory paymentFactory;
        switch (sale.getPayment().getPaymentMethod()) {
            case CARD:
                paymentFactory = new CardPaymentFactory(paymentByCardServiceImp);
                break;
            case PIX:
                paymentFactory = new PixPaymentFactory(paymentByPixServiceImp);
                break;
            default:
                throw new RuntimeException("Método de pagamento inválido");
        }
        PaymentProof paymentProof = paymentFactory.createPayment(sale);
        productService.subtractProducts(sale.getProductList());
        saleRepository.save(sale);

        return paymentProof;
    }

}
