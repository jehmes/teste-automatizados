package com.br.springtesteautomatizado.models;

import com.br.springtesteautomatizado.enums.PaymentMethodsEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethodsEnum paymentMethod;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "paymentProof_Product",
            joinColumns = @JoinColumn(name = "payment_proof_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList;

    public PaymentProof(Long id, LocalDate paymentDate, User user, BigDecimal amountPaid, PaymentMethodsEnum paymentMethod, List<Product> productList) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.user = user;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
        this.productList = productList;
    }

    public PaymentProof() {
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
    public List<Product> getProductList() {
        return productList;
    }
    public void setProductList(List<Product> productList) {
        this.productList = productList;
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
        PaymentProof that = (PaymentProof) o;
        return Objects.equals(id, that.id) && Objects.equals(paymentDate, that.paymentDate) && Objects.equals(user, that.user) && Objects.equals(amountPaid, that.amountPaid) && paymentMethod == that.paymentMethod && Objects.equals(productList, that.productList);
    }

    @Override
    public String toString() {
        return "PaymentProof{" +
                "id=" + id +
                ", paymentDate=" + paymentDate +
                ", user=" + user +
                ", amountPaid=" + amountPaid +
                ", paymentMethod=" + paymentMethod +
                ", productList=" + productList +
                '}';
    }
}
