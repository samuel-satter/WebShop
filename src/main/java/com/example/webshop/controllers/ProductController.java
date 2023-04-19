package com.example.webshop.controllers;

import com.example.webshop.entitys.Cart;
import com.example.webshop.entitys.OrderProduct;
import com.example.webshop.entitys.Product;
import com.example.webshop.repositorys.ProductRepository;
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
public class ProductController {

    private final ProductService productService;


    private final ProductRepository productRepository;

    @PostMapping("/admin")
    public String addProduct(@ModelAttribute("product") Product product) {
        productRepository.save(product);
        return "admin.html";
    }

    @GetMapping("/shop")
    public String productList(@RequestParam(name = "query", required = false) String query, Model model) {
        List<Product> productList;
        if (query == null || query.trim().isEmpty()) {
            productList = productService.getAllProducts();
        } else {
            productList = productService.searchForProducts(query);
        }
        model.addAttribute("productList", productList);
        return "shop.html";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("id_product") Long id, @RequestParam("quantity") int quantity, HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        Product product = productService.findById(id);

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProduct(product);
        orderProduct.setQuantity(quantity);

        return "redirect:/cart";
    }
}
