package com.example.webshop.services;

import com.example.webshop.entitys.*;
import com.example.webshop.model.Cart;
import com.example.webshop.repositorys.OrderRepository;
import com.example.webshop.repositorys.UserOrderRepository;
import com.example.webshop.repositorys.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDate;
import java.util.List;

@Service
@SessionScope
@Data
public class OrderService {

    private Cart cart;

    private User user;

    private final UserRepository userRepository;

    private final UserOrderRepository userOrderRepository;

    private final OrderRepository orderRepository;

    private final ProductService productService;

    @Autowired
    public OrderService(UserRepository userRepository, OrderRepository orderRepository, ProductService productService, Cart cart, UserOrderRepository userOrderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.cart = cart;
        this.userOrderRepository = userOrderRepository;
    }

    public void addOrder(User user) {
        user.addOrder(new UserOrder(cart.getOrderProducts(), user));
        user = userRepository.save(user);
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order getOrder(Long orderId ) {
        return orderRepository
                .findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("no order with that id"));
    }

    public void createOrder(User user, Cart cart) {
        Order order = new Order();
        order.setCustomerName(user.getUsername());
        order.setPrice(cart.getTotalPrice());
        order.setDateCreated(LocalDate.now());
        order.setUser(user);
        order.setShipped(false);
        int quantity = cart.getOrderProducts().stream()
                .map(OrderProduct:: getQuantity)
                .reduce(0, Integer::sum);
        order.setQuantity(quantity);
        orderRepository.save(order);
    }

    public void deleteProductFromListOfProducts(int id) {
        cart.deleteFromListOfOrderProducts(id);
    }

    public void addProductToOrder(Order order, Long productId, int quantity) {
        Product product = productService.findById(productId);
        OrderProduct orderProduct = getOrderProductFromOrder(order, productId);
        if (orderProduct != null) {
            orderProduct.setQuantity(orderProduct.getQuantity() + quantity);
        } else {
            orderProduct = new OrderProduct();
            orderProduct.setProduct(product);
            orderProduct.setQuantity(quantity);
            order.addOrderProduct(orderProduct);
        }
    }
    private OrderProduct getOrderProductFromOrder(Order order, Long productId) {
        for (OrderProduct orderProduct : order.getOrderProducts()) {
            if (orderProduct.getProduct().getId().equals(productId)) {
                return orderProduct;
            }
        }
        return null;
    }
}

