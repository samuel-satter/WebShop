package com.example.webshop.entitys;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "customer")
public class Customer {
    @Setter(AccessLevel.PROTECTED)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long id;

    @Column(name = "email_customer")
    @Email
    private String emailCustomer;

    @Column(name = "password_customer")
    private String passwordCustomer;

    @Column(name = "isAdmin_customer")
    private String isAdmin;


}
