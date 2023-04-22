package com.example.webshop.entitys;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_order")
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<OrderProduct> orderProducts;

    @ManyToOne
    User user;

    private boolean isShipped;

    public UserOrder(List<OrderProduct> orderProducts, User user) {
        this.orderProducts = orderProducts;
        this.user = user;
    }
}
