package com.example.webshop.entitys;

import lombok.Data;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@SessionScope
public class Cart {

    private List<OrderProduct> orderProductsList = new ArrayList<>();

    public BigDecimal getTotalPrice() {
        return orderProductsList.stream()
                .map(OrderProduct :: getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void clear() {
        orderProductsList.clear();
    }


}
