package com.example.webshop.entitys;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Setter(AccessLevel.PROTECTED)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Valid
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public void updateTotalPrice() {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderProduct orderProduct : orderProducts) {
            totalPrice = totalPrice.add(orderProduct.getTotalPrice());
        }
        this.price = totalPrice;
    }
    public void addOrderProduct(OrderProduct orderProduct) {
        this.orderProducts.add(orderProduct);
    }
    public OrderProduct getOrderProductByProductId(Long productId) {
        for (OrderProduct orderProduct : orderProducts) {
            if (orderProduct.getProduct().getId().equals(productId)) {
                return orderProduct;
            }
        }
        return null;
    }
}
