package com.br.springtesteautomatizado.exceptions;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException(String message) {
        super(message);
    }
}
