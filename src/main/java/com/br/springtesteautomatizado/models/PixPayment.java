package com.br.springtesteautomatizado.models;

import com.br.springtesteautomatizado.enums.PaymentMethodsEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "GP_PAYMENT_PIX")
@PrimaryKeyJoinColumn(name = "payment_id")
public class PixPayment extends Payment {
    @Column(nullable = false)
    private String pixKey;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDate expirationDate;

    public PixPayment() {}

    public PixPayment(Long id, LocalDate date, PaymentMethodsEnum paymentMethod, BigDecimal amount, String pixKey, String description, LocalDate expirationDate) {
        this.pixKey = pixKey;
        this.description = description;
        this.expirationDate = expirationDate;
    }
    public String getPixKey() {
        return pixKey;
    }

    public void setPixKey(String pixKey) {
        this.pixKey = pixKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
