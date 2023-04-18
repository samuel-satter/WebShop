package com.example.webshop.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Entity
public class OrderProduct {

    @Setter(AccessLevel.PROTECTED)

    @EmbeddedId
    @JsonIgnore
    private OrderProductPK pk;

    @Column(nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Transient
    public Product getProduct() {
        return this.pk.getProduct();
    }

    @Transient
    public BigDecimal getTotalPrice() {
        return getProduct().getPrice().multiply(BigDecimal.valueOf(getQuantity()));
    }


}
