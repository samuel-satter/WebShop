package com.example.webshop;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
    @Setter(AccessLevel.PROTECTED)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_id;

    @Column(name = "username_customer")
    private String username_customer;

    @Column(name = "password_customer")
    private String password_customer;


}
