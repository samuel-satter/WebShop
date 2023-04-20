package com.example.webshop.services;

import com.example.webshop.entitys.Order;
import com.example.webshop.entitys.OrderProduct;
import com.example.webshop.repositorys.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.List;

@Service
@SessionScope
public class OrderService {

    private final OrderRepository orderRepository;

    Order order;

    List<OrderProduct> productsInCart;

    public List<OrderProduct> getProductsInCart(){
        return productsInCart;
    }

    public List<OrderProduct> removeProductFromCart(int id) {
        if (getProductsInCart().get(id).getQuantity() == 1){
            getProductsInCart().remove(id);
            return getProductsInCart();
        } else {
            getProductsInCart().get(id).removeOneFromQunatity();
        }
        return getProductsInCart();
    }

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public BigDecimal getTotalOrderSum() {
        BigDecimal sum = BigDecimal.ZERO;
        List<OrderProduct> orderProducts1 = order.getOrderProducts();
        for (OrderProduct orderProduct : orderProducts1) {
            sum = sum.add(orderProduct.getTotalPrice());
        }
        return sum;
    }

    public List<OrderProduct> deleteFromListOfOrderProducts(int id) {
        order.getOrderProducts().remove(id);
        return order.getOrderProducts();
    }
}
