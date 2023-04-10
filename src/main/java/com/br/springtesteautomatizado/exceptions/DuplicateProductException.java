package com.br.springtesteautomatizado.exceptions;

public class DuplicateProductException extends Exception{

    public DuplicateProductException(String message) {
        super(message);
    }
}
