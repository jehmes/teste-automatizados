package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.enums.ProductsErrorsEnum;
import com.br.springtesteautomatizado.exceptions.ProductException;
import com.br.springtesteautomatizado.interfaces.IProductService;
import com.br.springtesteautomatizado.models.Product;
import com.br.springtesteautomatizado.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImp implements IProductService {

    @Autowired
    private ProductRepository productRepository;


    public void subtractProducts(List<Product> products) throws ProductException {
        List<Product> updateProducts = new ArrayList<>();

        for (Product product : products) {
            Product productFromDB = productRepository.findById(product.getId()).get();

            if (productFromDB.getQuantity() - product.getQuantity() == 0) {
                productFromDB.setQuantity(0);
                updateProducts.add(productFromDB);
            } else if (product.getQuantity() > productFromDB.getQuantity()) {
                throw new ProductException(ProductsErrorsEnum.ERROR_NEGATIVE_STOCK.getName());
            } else {
                productFromDB.setQuantity(productFromDB.getQuantity() - product.getQuantity());
                updateProducts.add(productFromDB);
            }
        }
        productRepository.saveAll(updateProducts);
    }

    @Override
    public Product createProduct() {
        Product p1 =new Product("Product 1", new BigDecimal("50.00"), 5);
        Product p2 =new Product("Product 2", new BigDecimal("30.00"), 5);
        Product p3 =new Product("Product 3", new BigDecimal("70.00"),5);
        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        return p1;
    }


}
