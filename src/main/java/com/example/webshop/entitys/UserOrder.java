package com.example.webshop.entitys;

import jakarta.persistence.*;

import java.util.List;


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

    boolean isShipped;


    public UserOrder(List<OrderProduct> orderProducts, User user) {
        this.orderProducts = orderProducts;
        this.user = user;
    }

    public UserOrder() {}


}
