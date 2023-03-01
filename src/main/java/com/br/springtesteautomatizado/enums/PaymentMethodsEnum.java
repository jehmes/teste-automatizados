package com.br.springtesteautomatizado.enums;

public enum PaymentMethodsEnum {

    CARD("CARTAO"),
    BOLETO("BOLETO"),
    PIX("PIX");

    private final String name;

    PaymentMethodsEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
