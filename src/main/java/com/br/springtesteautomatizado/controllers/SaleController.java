package com.br.springtesteautomatizado.controllers;

import com.br.springtesteautomatizado.enums.ProductsErrorsEnum;
import com.br.springtesteautomatizado.enums.UserErrorsEnum;
import com.br.springtesteautomatizado.exceptions.ProductException;
import com.br.springtesteautomatizado.exceptions.UserException;
import com.br.springtesteautomatizado.interfaces.ISaleService;
import com.br.springtesteautomatizado.models.PaymentProof;
import com.br.springtesteautomatizado.models.Product;
import com.br.springtesteautomatizado.models.Sale;
import com.br.springtesteautomatizado.models.User;
import com.br.springtesteautomatizado.repositories.ProductRepository;
import com.br.springtesteautomatizado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private ISaleService iSaleService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/save")
    public ResponseEntity<PaymentProof> save(@RequestBody Sale sale) throws Exception {
        User user = userRepository.findById(sale.getUser().getId())
                .orElseThrow(() -> new UserException(UserErrorsEnum.ERROR_FIND_USER.getName()));
        sale.setUser(user);

        for (Product product : sale.getProductList()) {
            productRepository.findById(product.getId()).orElseThrow(
                    () -> new ProductException(ProductsErrorsEnum.ERROR_FIND_PRODUCT.getName())
            );
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(iSaleService.saveSale(sale));
    }
}
