package com.br.springtesteautomatizado.models;

import com.br.springtesteautomatizado.enums.PaymentMethodsEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "GP_PAYMENT")
public class Payment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private LocalDate dateTime;
    @Column(nullable = false)
    private PaymentMethodsEnum paymentMethod;
    @OneToOne(cascade = CascadeType.ALL)
    private PaymentProof paymentProof;
    @Column(nullable = false)
    private BigDecimal amount;

    public Payment(Integer id, LocalDate date, PaymentMethodsEnum paymentMethod, PaymentProof paymentProof, BigDecimal amount) {
        this.id = id;
        this.dateTime = date;
        this.paymentMethod = paymentMethod;
        this.paymentProof = paymentProof;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public PaymentMethodsEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodsEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentProof getPaymentProof() {
        return paymentProof;
    }

    public void setPaymentProof(PaymentProof paymentProof) {
        this.paymentProof = paymentProof;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
