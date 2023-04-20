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

import java.util.Collections;
import java.util.List;

@Data
@Controller
public class ProductController {

    private final ProductService productService;


    private final ProductRepository productRepository;

    @GetMapping("/add-products")
    public String addProductsPage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("instruction", "Please add a product");
        return "add-products.html";
    }

    @PostMapping("/add-product")
    public String addProduct(@ModelAttribute("product") Product product, Model model) {
        productRepository.save(product);
        String feedback = String.format("Added %s", product);
        model.addAttribute("instruction", feedback);
        return "add-products.html";
    }
    @GetMapping("/shop")
    public String productList(@RequestParam(name = "productName", required = false) String productName,
                              @RequestParam(name = "selectedCategory", required = false) String selectedCategory,
                              Model model) {
        List<Product> productList;
        if (productName == null || productName.trim().isEmpty()) {
            if (selectedCategory == null || selectedCategory.trim().isEmpty()) {
                productList = productService.getAllProducts();
                model.addAttribute("categoryList", productService.getAllProductCategories());
                model.addAttribute("selectedCategory","");
            } else {
                productList = productService.searchForProducts(null, selectedCategory);
                model.addAttribute("categoryList", Collections.singletonList(selectedCategory));
                model.addAttribute("selectedCategory",selectedCategory);
            }
        } else {
            if (selectedCategory == null || selectedCategory.trim().isEmpty()) {
                productList = productService.searchForProducts(productName, null);
                model.addAttribute("categoryList", productService.getAllProductCategories());
                model.addAttribute("selectedCategory","");
            } else {
                productList = productService.searchForProducts(productName, selectedCategory);
                model.addAttribute("categoryList", Collections.singletonList(selectedCategory));
                model.addAttribute("selectedCategory",selectedCategory);
            }
        }
        model.addAttribute("productList", productList);
        return "shop";
    }

   
}
