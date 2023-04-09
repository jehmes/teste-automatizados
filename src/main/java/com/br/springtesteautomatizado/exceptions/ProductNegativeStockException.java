package com.br.springtesteautomatizado.exceptions;

public class ProductNegativeStockException extends Exception{

    public ProductNegativeStockException(String message) {
        super(message);
    }
}
