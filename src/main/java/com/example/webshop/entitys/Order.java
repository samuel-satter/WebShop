package com.example.webshop.entitys;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

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

    private LocalDate dateCreated;

    @OneToMany(mappedBy = "pk.order")
    @Valid
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public Double getTotalOrderSum() {
        double sum = 0;
        List<OrderProduct> orderProducts1 = getOrderProducts();
        for (OrderProduct orderProduct : orderProducts1) {
            sum += orderProduct.getTotalPrice();
        }
        return sum;
    }


}
