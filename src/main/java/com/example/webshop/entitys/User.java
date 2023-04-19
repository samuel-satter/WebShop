package com.example.webshop.entitys;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "users")
public class User {
    @Setter(AccessLevel.PROTECTED)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "username")
    @Email
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_admin_user")
    private boolean isUserAdmin;

}
