package com.br.springtesteautomatizado.enums;

public enum PaymentErrorsEnum {

    CARD_INVALID("CARTÃO INVÁLIDO"),
    PIX_INVALID("PIX INVÁLIDO");

    private final String name;

    PaymentErrorsEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
