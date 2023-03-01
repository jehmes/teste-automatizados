package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.enums.ProductsErrorsEnum;
import com.br.springtesteautomatizado.enums.UserErrorsEnum;
import com.br.springtesteautomatizado.exceptions.ProductException;
import com.br.springtesteautomatizado.exceptions.UserException;
import com.br.springtesteautomatizado.interfaces.ISaleService;
import com.br.springtesteautomatizado.models.Product;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.models.User;
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
    SaleRepository saleRepository;

    @Autowired
    ProductServiceImp productService;

    @Autowired
    UserRepository userRepository;


    @Override
    public void saveSale(Sale sale) throws Exception {
        User user = userRepository.findById(sale.getUser().getId())
                .orElseThrow(() -> new UserException(UserErrorsEnum.ERROR_FIND_USER.getName()));
        sale.setUser(user);

        for (Product product : sale.getProductList()) {
            productRepository.findById(product.getId()).orElseThrow(
                    () -> new ProductException(ProductsErrorsEnum.ERROR_FIND_PRODUCT.getName())
            );
        }

        saleRepository.save(sale);
        productService.subtractProducts(sale.getProductList());
    }

}
