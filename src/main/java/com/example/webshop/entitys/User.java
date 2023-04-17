package com.example.webshop.entitys;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "user")
public class User {
    @Setter(AccessLevel.PROTECTED)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "email_user")
    @Email
    private String emailUser;

    @Column(name = "password_user")
    private String passwordUser;

    @Column(name = "isAdmin_user")
    private boolean isUserAdmin;

}
