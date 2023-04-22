package com.example.webshop.controllers;

import com.example.webshop.entitys.User;
import com.example.webshop.model.Cart;
import com.example.webshop.entitys.Order;
import com.example.webshop.services.OrderService;
import com.example.webshop.services.ProductService;
import com.example.webshop.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Data
@Controller
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;

    private final UserService userService;

    private final Cart cart;

    Order order;

    @GetMapping("/cart")
    public String goToCart(Model model, HttpSession session) {
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
        orderService.deletePrdouctFromListOfProducts(index);
        model.addAttribute("quantity", orderService.getCart().getTotalPrice());
        model.addAttribute("cart", orderService.getCart());

        return "cart.html";
    }
//    @PostMapping("/remove-all-products-from-cart")
//    public String removeAllProductsFromCart(){
//
//    }

    @PostMapping("/confirm-order")
    public String confirmOrder(HttpSession session, Model model) throws Exception {
        String username = (String) session.getAttribute("username");
        User user = userService.findByUsername(username).orElseThrow();
        orderService.addOrder(user);
//        Order order = orderService.createOrder(user, cart);
//        orderService.saveOrder(order);
//        session.removeAttribute("order");
//        cart.clearCart();
//        model.addAttribute("orderId", order.getId());
//        model.addAttribute("totalPrice", order.getPrice());
//        model.addAttribute("customerName", user.getUsername());
        model.addAttribute("cart", cart);
        return "confirmed-order.html";
    }


    @GetMapping("/confirmed-order")
    public String showConfirmedOrder(Model model, HttpSession session) {
//        model.addAttribute("order", order);
        model.addAttribute("cart", cart);
        String customerName = (String) session.getAttribute("username");
        model.addAttribute("customerName", customerName);
        return "confirmed-order.html";
    }
}
