package com.br.springtesteautomatizado.enums;

public enum UserErrorsEnum {

    USER_NOT_FOUND("User not found"),
    CPF_ALREADY_EXISTS("CPF already exists");

    private final String name;

    UserErrorsEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
