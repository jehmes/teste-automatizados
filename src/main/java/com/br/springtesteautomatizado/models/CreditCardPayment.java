package com.br.springtesteautomatizado.models;

import com.br.springtesteautomatizado.enums.PaymentMethodsEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "GP_PAYMENT_CREDIT_CARD")
@PrimaryKeyJoinColumn(name = "payment_id")
public class CreditCardPayment extends Payment{
    @Column(nullable = false)
    private String cardNumber;
    @Column(nullable = false)
    private String cvv;
    @Column(nullable = false)
    private String holderName;

    public CreditCardPayment(LocalDateTime date, PaymentMethodsEnum paymentMethod, BigDecimal amount, String cardNumber, String cvv, String holderName) {
        super(date, paymentMethod, amount);
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.holderName = holderName;
    }

    public CreditCardPayment() {
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }


    @Override
    public String toString() {
        return "CreditCardPayment{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cvv='" + cvv + '\'' +
                ", holderName='" + holderName + '\'' +
                "} " + super.toString();
    }
}
