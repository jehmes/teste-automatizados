package com.br.springtesteautomatizado.interfaces;

import com.br.springtesteautomatizado.exceptions.DuplicateProductExcpetion;
import com.br.springtesteautomatizado.models.Product;

import java.util.List;

public interface IProductService {

    void subtractProducts(List<Product> products) throws Exception;
    public Iterable<Product> saveProductList(List<Product> products) throws DuplicateProductExcpetion;
}
