package com.example.webshop.entitys;

import lombok.Data;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.List;

@Data
@SessionScope
public class Cart {

    OrderProduct orderProduct;
    private List<OrderProduct> orderProducts;

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }
    public void addOrderProduct(OrderProduct orderProduct) {
        orderProducts.add(orderProduct);
    }

    public BigDecimal getTotalPrice() {
        return orderProducts.stream()
                .map(OrderProduct :: getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void clear() {
        orderProducts.clear();
    }


}
