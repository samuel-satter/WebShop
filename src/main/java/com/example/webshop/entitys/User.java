package com.example.webshop.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "admin")
    private boolean admin;

    @Email
    @Column(name = "email")
    private String email;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<UserOrder> userOrders;

    public User() {
      userOrders = new ArrayList<>();
    }

    public User(String username){
        this.username = username;
    }

    public void addOrder(UserOrder userOrder) {
        userOrders.add(userOrder);
    }
}
