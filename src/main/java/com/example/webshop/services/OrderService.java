package com.example.webshop.services;

import com.example.webshop.entitys.Cart;
import com.example.webshop.entitys.Order;
import com.example.webshop.entitys.OrderProduct;
import com.example.webshop.entitys.Product;
import com.example.webshop.repositorys.OrderRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@SessionScope
@Data
public class OrderService {

    private final OrderRepository orderRepository;

    private Order order;
    private Cart cart;

    private final ProductService productService;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductService productService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public void createOrder() {
        if (order == null) {
            order = new Order();
        }
    }

    public void createCart(Cart cart) {
        cart = new Cart();
    }

//    public void addToCart(Product product) {
//        cart.addProduct(product);
//    }

    public List<OrderProduct> getProductsInCart() {
        return cart.getOrderProducts();
    }

//    public List<OrderProduct> removeProductFromCart(int id) {
//        cart.removeProduct(id);
//        return getProductsInCart();
//    }

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

    public Order saveCart(List<OrderProduct> orderProducts, String customerName) {
        createOrder();
        order.setCustomerName(customerName);
        order.setOrderProducts(orderProducts);
        order.setDateCreated(LocalDate.now());
        order.updateTotalPrice();
        for (OrderProduct orderProduct : order.getOrderProducts()) {
            if (orderProduct.getProduct().getProductName() == null) {
                throw new IllegalArgumentException("Product name cannot be null");
            }
        }
        orderRepository.save(order);
        return order;
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

