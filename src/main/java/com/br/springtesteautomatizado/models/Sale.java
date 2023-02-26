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
    @Column(nullable = false)
    private Cart cart;
    @Column(nullable = false)
    private User user;
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private BigDecimal totalPrice;

    public Sale(Cart cart, User user, LocalDate date, BigDecimal totalPrice) {
        this.cart = cart;
        this.user = user;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

}
