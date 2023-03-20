package com.br.springtesteautomatizado.models;

import com.br.springtesteautomatizado.enums.PaymentMethodsEnum;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "GP_PAYMENT")
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "paymentMethod",
        use = JsonTypeInfo.Id.NAME,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreditCardPayment.class, name = "CARD"),
        @JsonSubTypes.Type(value = PixPayment.class, name = "PIX")
})
public class Payment {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private LocalDate paymentDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethodsEnum paymentMethod;
    @Column(nullable = false)
    private BigDecimal amount;

    public Payment(Long id, LocalDate date, PaymentMethodsEnum paymentMethod, BigDecimal amount) {
        this.id = id;
        this.paymentDate = date;
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

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentMethodsEnum getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodsEnum paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id) && Objects.equals(paymentDate, payment.paymentDate) && Objects.equals(amount, payment.amount);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", dateTime=" + paymentDate +
                ", amount=" + amount +
                '}';
    }
}
