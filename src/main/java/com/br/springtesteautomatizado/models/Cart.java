package com.br.springtesteautomatizado.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Cart {

    private Long id;
    private List<Product> products = new ArrayList<>();
    private BigDecimal amount;
    private User user;

    public Cart(Long id, List<Product> products, BigDecimal amount, User user) {
        this.id = id;
        this.products = products;
        this.amount = amount;
        this.user = user;
    }

    public Cart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
