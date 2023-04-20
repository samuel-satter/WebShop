package com.example.webshop.controllers;

import com.example.webshop.entitys.Cart;
import com.example.webshop.entitys.Order;
import com.example.webshop.entitys.OrderProduct;
import com.example.webshop.entitys.Product;
import com.example.webshop.services.OrderService;
import com.example.webshop.services.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Data
@Controller
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping("/cart")
    public String goToCart(Model model, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        System.out.println("Cart object in session: " + cart);
        model.addAttribute("cart", cart);
        return "cart.html";
    }

    @PostMapping("/add-to-cart")
    public String addOrderToCart(@RequestParam("productId") Long productId,
                            @RequestParam(value = "quantity", defaultValue = "1") Integer quantity,
                            HttpSession session) {
        Order order = (Order) session.getAttribute("order");
        if (order != null) {
            System.out.println("OrderProduct added to cart: " + order.getOrderProducts());
            orderService.addProductToOrder(order, productId, quantity);
            session.setAttribute("order", order);
        }
        return "redirect:/shop";
    }

    @PostMapping("/confirm-order")
    public String confirmOrder(HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        String customerName = (String) session.getAttribute("username");
        List<OrderProduct> orderProducts = cart.getOrderProducts();
        Order order = orderService.saveCart(orderProducts, customerName);
        cart.clear();
        session.setAttribute("cart", cart);
        String feedback = String.format("Order #%d confirmed", order.getId());
        model.addAttribute("feedback", feedback);

        return "redirect:/confirmed-order?orderId=" + order.getId();
    }


    @GetMapping("/confirmed-order")
    public String showConfirmedOrder(@RequestParam Long orderId, @RequestParam Double totalPrice, Model model, HttpSession session) {
        model.addAttribute("orderId", orderId);
        model.addAttribute("totalPrice", totalPrice);
        String customerName = (String) session.getAttribute("username");
        model.addAttribute("customerName", customerName);
        return "confirmed-order.html";
    }
}
