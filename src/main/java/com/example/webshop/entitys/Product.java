package com.example.webshop.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "name")
    private String productName;

    @Column(name = "price")
    private BigDecimal productPrice;

    @Column(name = "quantity")
    private int productQuantity;

    @Column(name = "category")
    private String productCategory;

    public BigDecimal getPrice() {
        return productPrice;
    }
}