package com.example.webshop.controllers;

import com.example.webshop.entitys.Cart;
import com.example.webshop.entitys.Order;
import com.example.webshop.entitys.OrderProduct;
import com.example.webshop.entitys.Product;
import com.example.webshop.repositorys.ProductRepository;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Controller
public class ProductController {

    private final ProductService productService;


    private final ProductRepository productRepository;

    private final OrderService orderService;

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

    @PostMapping("/add-product-to-cart")
    public String addProductToCart(@RequestParam("productId") Long productId,
                            @RequestParam(value = "quantity", defaultValue = "1") Integer quantity,
                            HttpSession session) {
        Product product = productService.findById(productId);
        Order order = (Order) session.getAttribute("order");
        if (product != null && order != null) {
            OrderProduct orderProduct = order.getOrderProductByProductId(productId);
            if (orderProduct != null) {
                orderProduct.setQuantity(orderProduct.getQuantity() + quantity);
            } else {
                orderProduct = new OrderProduct();
                orderProduct.setProduct(product);
                orderProduct.setQuantity(quantity);
                order.addOrderProduct(orderProduct);
            }
            session.setAttribute("order", order);
        }
        return "shop.html";
    }
    @GetMapping("/shop")
    public String productList(@RequestParam(name = "productName", required = false) String productName,
                              @RequestParam(name = "selectedCategory", required = false) String selectedCategory,
                              Model model) {
        List<Product> productList = productService.searchForProducts(productName, selectedCategory);
        model.addAttribute("productList", productList);
        model.addAttribute("categoryList", productService.getAllProductCategories());
        model.addAttribute("selectedCategory", selectedCategory);

        return "shop";
    }

   
}
