package com.example.webshop.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Entity
public class OrderProduct {

    @EmbeddedId
    private OrderProductPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @NotNull
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    public OrderProduct(Long productId, int quantity) {

    }

    public OrderProduct() {

    }

    public void removeOneFromQunatity() {
        this.quantity--;
    }

    public BigDecimal getTotalPrice() {
        return getProduct().getPrice().multiply(BigDecimal.valueOf(getQuantity()));
    }

    public void addQuantity(int quantity) {
        this.quantity = this.quantity + quantity;
    }

}

