package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.enums.ProductsErrorsEnum;
import com.br.springtesteautomatizado.exceptions.DuplicateProductExcpetion;
import com.br.springtesteautomatizado.exceptions.ProductException;
import com.br.springtesteautomatizado.interfaces.IProductService;
import com.br.springtesteautomatizado.models.Product;
import com.br.springtesteautomatizado.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements IProductService {

    @Autowired
    private ProductRepository productRepository;


    public void subtractProducts(List<Product> products) throws ProductException {
        List<Product> updateProducts = new ArrayList<>();

        for (Product product : products) {
            Optional<Product> productFromDB = productRepository.findById(product.getId());
            if (productFromDB.isEmpty()) {
                throw new ProductException("Not Found");
            }

            if (productFromDB.get().getQuantity() - product.getQuantity() == 0) {
                productFromDB.get().setQuantity(0);
                updateProducts.add(productFromDB.get());
            } else if (product.getQuantity() > productFromDB.get().getQuantity()) {
                throw new ProductException(ProductsErrorsEnum.ERROR_NEGATIVE_STOCK.getName());
            } else {
                productFromDB.get().setQuantity(productFromDB.get().getQuantity() - product.getQuantity());
                updateProducts.add(productFromDB.get());
            }
        }
        productRepository.saveAll(updateProducts);
    }

    @Override
    public List<Product> saveProductList(List<Product> products) throws DuplicateProductExcpetion {
        Optional<Product> productOptional = productRepository.findByNames(products);
        if (productOptional.isPresent()) {
            throw new DuplicateProductExcpetion();
        }
        return (List<Product>) productRepository.saveAll(products);
    }

}
