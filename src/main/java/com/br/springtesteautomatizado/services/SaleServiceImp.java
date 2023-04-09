package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.enums.ProductsErrorsEnum;
import com.br.springtesteautomatizado.enums.UserErrorsEnum;
import com.br.springtesteautomatizado.exceptions.PaymentException;
import com.br.springtesteautomatizado.exceptions.ProductException;
import com.br.springtesteautomatizado.exceptions.UserException;
import com.br.springtesteautomatizado.factorys.PaymentFactory;
import com.br.springtesteautomatizado.interfaces.IProductService;
import com.br.springtesteautomatizado.interfaces.ISaleService;
import com.br.springtesteautomatizado.models.Payment;
import com.br.springtesteautomatizado.models.Product;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.models.User;
import com.br.springtesteautomatizado.repositories.ProductRepository;
import com.br.springtesteautomatizado.repositories.SaleRepository;
import com.br.springtesteautomatizado.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImp implements ISaleService {

    private final SaleRepository saleRepository;
    private final IProductService productService;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public SaleServiceImp(SaleRepository saleRepository, IProductService productService, ProductRepository productRepository, UserRepository userRepository) {
        this.saleRepository = saleRepository;
        this.productService = productService;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Payment saveSale(Sale sale) throws UserException, ProductException, PaymentException {
        User user = userRepository.findById(sale.getUser().getId())
                .orElseThrow(() -> new UserException(UserErrorsEnum.ERROR_FIND_USER.getName()));
        sale.setUser(user);

        List<String> productsNames = sale.getProductList().stream().map(Product::getName).collect(Collectors.toList());
        Integer productsFromDb = productRepository.findByProductsName(productsNames);
        if (productsFromDb > 0) {
            throw new ProductException(ProductsErrorsEnum.ERROR_FIND_PRODUCT.getName());
        }

        Payment payment = PaymentFactory.createPaymentType(sale);
        productService.subtractProducts(sale.getProductList());
        saleRepository.save(sale);
        return payment;
    }

}
