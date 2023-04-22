package com.example.webshop.entitys;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private BigDecimal price;

    private boolean isShipped;
    @Column(name = "date_created")
    private LocalDate dateCreated;

    @OneToMany(cascade = CascadeType.ALL)
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
