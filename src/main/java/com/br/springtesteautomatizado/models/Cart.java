package com.br.springtesteautomatizado.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Cart {

    private Integer id;
    private List<Product> products = new ArrayList<>();
    private BigDecimal totalPrice;
    private User user;

    public Cart(Integer id, List<Product> products, BigDecimal totalPrice, User user) {
        this.id = id;
        this.products = products;
        this.totalPrice = totalPrice;
        this.user = user;
    }

    public Cart() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
