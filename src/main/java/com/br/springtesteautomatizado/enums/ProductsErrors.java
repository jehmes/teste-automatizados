package com.br.springtesteautomatizado.enums;

public enum ProductsErrors {

    ERROR_FIND_PRODUCT("Erro ao encontrar o produto"),
    ERROR_NEGATIVE_STOCK("Quantidade do produto comprado deve ser menor que a quantidade em estoque");

    private final String name;

    ProductsErrors(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
