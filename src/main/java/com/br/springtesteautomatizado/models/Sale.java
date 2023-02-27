package com.br.springtesteautomatizado.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "vendas")
public class Sale {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @Transient
    private Cart cart;
    @ManyToOne
    private User user;
    @Column(nullable = false)
    private LocalDate dateTime;
    @Column(nullable = false)
    private BigDecimal totalPrice;

    public Sale(Cart cart, User user, LocalDate dateTime, BigDecimal totalPrice) {
        this.cart = cart;
        this.user = user;
        this.dateTime = dateTime;
        this.totalPrice = totalPrice;
    }

    public Sale() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

}
