package com.br.springtesteautomatizado.enums;

public enum PaymentErrorsEnum {

    CARD_INVALID("Cartão inválido"),
    PIX_INVALID("PIX inválido");

    private final String name;

    PaymentErrorsEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
