package com.br.springtesteautomatizado.enums;

public enum UserErrorsEnum {

    ERROR_FIND_USER("Erro ao encontrar o usuário");

    private final String name;

    UserErrorsEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
