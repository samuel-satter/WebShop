package com.example.webshop.controllers;

import com.example.webshop.entitys.Order;
import com.example.webshop.entitys.User;
import com.example.webshop.model.Cart;
import com.example.webshop.services.OrderService;
import com.example.webshop.services.ProductService;
import com.example.webshop.services.UserOrderService;
import com.example.webshop.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Data
@Controller
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;

    private final UserService userService;

    private final UserOrderService userOrderService;

    private final Cart cart;

    Order order;

    @GetMapping("/cart")
    public String goToCart(Model model) {
        System.out.println("Cart object in session: " + cart);
        model.addAttribute("cart", cart);
        return "cart.html";
    }

    @PostMapping("/add-to-cart")
    public String addOrderToCart(@RequestParam("productId") Long productId,
                            @RequestParam(value = "quantity", defaultValue = "1") Integer quantity,
                            HttpSession session) {
        order = (Order) session.getAttribute("order");
        if (order != null) {
            System.out.println("OrderProduct added to cart: " + order.getOrderProducts());
            orderService.addProductToOrder(order, productId, quantity);
            session.setAttribute("order", order);
        }
        return "redirect:/shop";
    }

    @PostMapping("/remove-product-from-cart")
    public String removeProductFromCart(@RequestParam("index") int index, Model model) {
        orderService.deleteProductFromListOfProducts(index);
        model.addAttribute("quantity", orderService.getCart().getTotalPrice());
        model.addAttribute("cart", orderService.getCart());

        return "cart.html";
    }

    @PostMapping("/confirm-order")
    public String confirmOrder(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        User user = userService.findByUsername(username).orElseThrow();
        orderService.createOrder(user, cart);
        orderService.addOrder(user);
        model.addAttribute("cart", cart);
        return "confirmed-order.html";
    }


    @GetMapping("/confirmed-order")
    public String showConfirmedOrder(Model model, HttpSession session) {
        model.addAttribute("cart", cart);
        String customerName = (String) session.getAttribute("username");
        model.addAttribute("customerName", customerName);
        return "confirmed-order.html";
    }

    @GetMapping("/order-list")
    public String showListOfOrders(Model model) {
        List<Order> orders = orderService.getOrderRepository().findAll();
        System.out.println(orders);
        model.addAttribute("orders", orders);
        return "order-list.html";
    }

    @PostMapping("/confirm-shipping")
    public String confirmShipping(@RequestParam(name = "orderId")Long orderId, Model model) {
        System.out.println(orderId);
        System.out.println(model);
        Order order = orderService.getOrder(orderId);
        order.setShipped(true);
        orderService.saveOrder(order);
        List<Order> orders = orderService.getOrderRepository().findAll();
        model.addAttribute("orders", orders);
       return "order-list.html";
    }
}
