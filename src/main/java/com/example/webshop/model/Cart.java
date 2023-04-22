package com.example.webshop.model;

import com.example.webshop.entitys.Order;
import com.example.webshop.entitys.OrderProduct;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@SessionScope
@Component
public class Cart {

    OrderProduct orderProduct;
    private List<OrderProduct> orderProducts;

    private Order order;

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }
    public Cart() {
        orderProducts = new ArrayList<>();
    }
    public void addOrderProduct(OrderProduct orderProduct) {
        if (orderProducts.size() > 0) {
            List<OrderProduct> currentProduct = orderProducts.stream()
                    .filter(orderProduct1 -> orderProduct1.getProduct()
                            .getId().equals(orderProduct.getProduct().getId()))
                    .toList();
            if (currentProduct.size() > 0) {
                currentProduct.get(0).addQuantity(orderProduct.getQuantity());
            } else {
                orderProducts.add(orderProduct);
            }
        } else {
            orderProducts.add(orderProduct);
        }
    }

    public void deleteFromListOfOrderProducts(int index) {
        if (getOrderProducts().get(index).getQuantity() == 1) {
            getOrderProducts().remove(index);
        } else {
            getOrderProducts().get(index).removeOneFromQuantity();
        }
    }

    public BigDecimal getTotalPrice() {
        return orderProducts.stream()
                .map(OrderProduct :: getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
