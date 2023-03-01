package com.br.springtesteautomatizado.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "GP_PAYMENT_PROOF")
public class PaymentProof {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private LocalDate paymentDate;
    @ManyToOne
    private User user;
    @Column(nullable = false)
    private BigDecimal amountPaid;

    public PaymentProof() {
    }
    public PaymentProof(Long id, LocalDate paymentDate, User user, BigDecimal amountPaid) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.user = user;
        this.amountPaid = amountPaid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }
}
