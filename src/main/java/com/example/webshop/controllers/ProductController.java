package com.example.webshop.controllers;

import com.example.webshop.entitys.Product;
import com.example.webshop.services.ProductService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Data
@Controller
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public String productList(Model model) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("productList", productList);
        return "shop.html";
    }
}
