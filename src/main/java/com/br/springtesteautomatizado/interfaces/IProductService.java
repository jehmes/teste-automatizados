package com.br.springtesteautomatizado.interfaces;

import com.br.springtesteautomatizado.models.Product;

import java.util.List;

public interface IProductService {

    void subtractProducts(List<Product> products) throws Exception;
}
