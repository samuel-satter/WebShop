package com.example.webshop.services;

import com.example.webshop.entitys.*;
import com.example.webshop.model.Cart;
import com.example.webshop.repositorys.OrderRepository;
import com.example.webshop.repositorys.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
@Data
public class OrderService {



    private Order order;
    private Cart cart;

    private User user;

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    private final ProductService productService;

    private final OrderProductPK orderProductPK;

    @Autowired
    public OrderService(UserRepository userRepository, OrderRepository orderRepository, ProductService productService, OrderProductPK orderProductPK, Cart cart) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.orderProductPK = orderProductPK;
        this.cart = cart;
    }

    public void addOrder(User user) {
        user.addOrder(new UserOrder(cart.getOrderProducts(), user));
        user = userRepository.save(user);
    }



//    public void createCart(Cart cart) {
//        cart = new Cart();
//    }

    public List<OrderProduct> getProductsInCart() {
        return cart.getOrderProducts();
    }


    public BigDecimal getTotalOrderSum() {
        BigDecimal sum = BigDecimal.ZERO;
        List<OrderProduct> orderProducts1 = order.getOrderProducts();
        for (OrderProduct orderProduct : orderProducts1) {
            sum = sum.add(orderProduct.getTotalPrice());
        }
        return sum;
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order createOrder(User user, Cart cart) {
        Order order = new Order();
        order.setUser(user);
        order.setCustomerName(user.getUsername());
        List<OrderProduct> orderProducts = new ArrayList<>(cart.getOrderProducts());
        order.setDateCreated(LocalDate.now());
        order.setOrderProducts(orderProducts);
        order.updateTotalPrice();

        return order;
    }
    public List<OrderProduct> deletePrdouctFromListOfProducts(int id) {
        return cart.deleteFromListOfOrderProducts(id);
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

