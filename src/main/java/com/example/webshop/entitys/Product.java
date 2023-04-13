package com.example.webshop.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
public class Product {
    @Setter(AccessLevel.PROTECTED)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Product has to have a name")
    @Basic(optional = false)
    private String name;

    private Double price;

}
