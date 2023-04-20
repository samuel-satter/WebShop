package com.example.webshop.controllers;

import com.example.webshop.entitys.Cart;
import com.example.webshop.entitys.OrderProduct;
import com.example.webshop.entitys.Product;
import com.example.webshop.services.OrderService;
import com.example.webshop.services.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@Controller
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping("/cart")
    public String goToCart(Model model, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        model.addAttribute("cart", cart);
        return "cart.html";
    }
    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("id_product") Long id, @RequestParam("quantity") int quantity,
                            HttpSession session, Model model) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        Product product = productService.findById(id);

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setQuantity(quantity);

        cart.addOrderProduct(orderProduct);
        String feedback = String.format("Added %s to cart", product);
        model.addAttribute("feedback", feedback);
        model.addAttribute("cart", cart);
        
        return "shop.html";
    }
}
