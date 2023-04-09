package com.br.springtesteautomatizado.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "GP_PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Integer quantity;
    @ManyToMany(mappedBy = "productList", cascade = CascadeType.ALL)
    private List<Sale> saleList;
    @ManyToMany(mappedBy = "productList", cascade = CascadeType.ALL)
    private List<PaymentProof> paymentProofList;

    public Product(String name, BigDecimal value, Integer quantity) {
        this.name = name;
        this.price = value;
        this.quantity = quantity;
    }

    public Product(Long id, String name, BigDecimal value, Integer quantity) {
        this.id = id;
        this.name = name;
        this.price = value;
        this.quantity = quantity;
    }
    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Sale> getSaleList() {
        return saleList;
    }

    public void setSaleList(List<Sale> saleList) {
        this.saleList = saleList;
    }

    public List<PaymentProof> getPaymentProofList() {
        return paymentProofList;
    }
    public void setPaymentProofList(List<PaymentProof> paymentProofList) {
        this.paymentProofList = paymentProofList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && Objects.equals(price, product.price) && Objects.equals(quantity, product.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, quantity);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
