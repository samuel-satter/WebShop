package com.example.webshop.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
public class OrderProduct {

    @Setter(AccessLevel.PROTECTED)

    @EmbeddedId
    @JsonIgnore
    private OrderProductPK pk;

    @Column(nullable = false)
    private Integer quantity;

    @Transient
    public Product getProduct() {
        return this.pk.getProduct();
    }

    @Transient
    public Double getTotalPrice() {
        return getProduct().getPrice() * getQuantity();
    }


}
