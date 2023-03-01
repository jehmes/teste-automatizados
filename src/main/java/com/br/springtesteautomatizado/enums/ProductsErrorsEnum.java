package com.br.springtesteautomatizado.enums;

public enum ProductsErrorsEnum {

    ERROR_FIND_PRODUCT("Erro ao encontrar o produto"),
    ERROR_NEGATIVE_STOCK("Quantidade do produto comprado deve ser menor que a quantidade em estoque");

    private final String name;

    ProductsErrorsEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
