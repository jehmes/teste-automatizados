package com.br.springtesteautomatizado.models;

import com.br.springtesteautomatizado.enums.PaymentMethodsEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    @Column(nullable = false)
    private LocalDate expirationDate;

    public CreditCardPayment(Long id, LocalDate date, PaymentMethodsEnum paymentMethod, BigDecimal amount, String cardNumber, String cvv, String holderName, LocalDate expirationDate) {
        super(id, date, paymentMethod, amount);
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.holderName = holderName;
        this.expirationDate = expirationDate;
    }

    public CreditCardPayment(String cardNumber, String cvv, String holderName, LocalDate expirationDate) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.holderName = holderName;
        this.expirationDate = expirationDate;
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

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "CreditCardPayment{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cvv='" + cvv + '\'' +
                ", holderName='" + holderName + '\'' +
                ", expirationDate=" + expirationDate +
                "} " + super.toString();
    }
}
