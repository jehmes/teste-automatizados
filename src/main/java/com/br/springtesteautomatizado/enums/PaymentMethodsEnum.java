package com.br.springtesteautomatizado.enums;

public enum PaymentMethodsEnum {

    CARD("CARTAO"),
    BOLETO("BOLETO"),
    PIX("PIX"),
    INVALID_PAYMENT_METHOD("METODO INVALIDO");

    private final String name;

    PaymentMethodsEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
