package com.br.springtesteautomatizado.models;

import com.br.springtesteautomatizado.enums.PaymentMethodsEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "GP_PAYMENT")
public class Payment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private LocalDate dateTime;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethodsEnum paymentMethod;
    @Column(nullable = false)
    private BigDecimal amount;

    public Payment(Long id, LocalDate date, PaymentMethodsEnum paymentMethod, BigDecimal amount) {
        this.id = id;
        this.dateTime = date;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
    }

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(dateTime, payment.dateTime) && paymentMethod == payment.paymentMethod && Objects.equals(amount, payment.amount);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", paymentMethod=" + paymentMethod +
                ", amount=" + amount +
                '}';
    }
}
