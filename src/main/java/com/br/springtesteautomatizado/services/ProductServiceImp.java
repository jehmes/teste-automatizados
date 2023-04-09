package com.br.springtesteautomatizado.services;

import com.br.springtesteautomatizado.enums.ProductsErrorsEnum;
import com.br.springtesteautomatizado.exceptions.DuplicateProductExcpetion;
import com.br.springtesteautomatizado.exceptions.ProductNegativeStockException;
import com.br.springtesteautomatizado.exceptions.ProductNotFoundException;
import com.br.springtesteautomatizado.interfaces.IProductService;
import com.br.springtesteautomatizado.models.Product;
import com.br.springtesteautomatizado.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements IProductService {

    @Autowired
    private ProductRepository productRepository;


    public void subtractProducts(List<Product> products) throws ProductNotFoundException, ProductNegativeStockException {
        List<Product> updateProducts = new ArrayList<>();

        for (Product product : products) {
            Optional<Product> productFromDB = productRepository.findById(product.getId());
            if (productFromDB.isEmpty()) {
                throw new ProductNotFoundException(ProductsErrorsEnum.ERROR_FIND_PRODUCT.getName());
            }

            if (productFromDB.get().getQuantity() - product.getQuantity() == 0) {
                productFromDB.get().setQuantity(0);
                updateProducts.add(productFromDB.get());
            } else if (product.getQuantity() > productFromDB.get().getQuantity()) {
                throw new ProductNegativeStockException(ProductsErrorsEnum.ERROR_NEGATIVE_STOCK.getName());
            } else {
                productFromDB.get().setQuantity(productFromDB.get().getQuantity() - product.getQuantity());
                updateProducts.add(productFromDB.get());
            }
        }
        productRepository.saveAll(updateProducts);
    }

    @Override
    public List<Product> saveProductList(List<Product> products) throws DuplicateProductExcpetion {
        List<String> productsNames = products.stream().map(Product::getName).collect(Collectors.toList());
        Integer existProduct = productRepository.findByProductsByName(productsNames);
        if (existProduct > 0) {
            throw new DuplicateProductExcpetion("Product already exists");
        }
        return (List<Product>) productRepository.saveAll(products);
    }

}
