package com.br.springtesteautomatizado.interfaces;

import com.br.springtesteautomatizado.exceptions.DuplicateProductException;
import com.br.springtesteautomatizado.exceptions.ProductNegativeStockException;
import com.br.springtesteautomatizado.exceptions.ProductNotFoundException;
import com.br.springtesteautomatizado.models.Product;

import java.util.List;

public interface IProductService {

    void subtractProducts(List<Product> products) throws ProductNotFoundException, ProductNegativeStockException;
    public Iterable<Product> saveProductList(List<Product> products) throws DuplicateProductException;
}
