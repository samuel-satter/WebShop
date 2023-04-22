package com.example.webshop.entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_products")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("orderId")
//    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private Integer quantity;

//    @Column(nullable = false)
//    private BigDecimal price;


//    public OrderProduct(Order order, Product product, Integer quantity) {
//        id = new OrderProductPK();
//        id.setOrder(order);
//        id.setProduct(product);
//        this.quantity = quantity;
//    }


    public OrderProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
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

