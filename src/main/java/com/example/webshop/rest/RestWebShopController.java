package com.example.webshop.rest;

import com.example.webshop.entitys.Product;
import com.example.webshop.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class RestWebShopController {

    private final ProductService productService;

    @Autowired
    public RestWebShopController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/rest/all-products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/rest/get-by-id/{id}")
    public ResponseEntity<String> getProductById(@PathVariable Long id) {
        if (productService.getProductRepository().findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            productService.findById(id);
            return ResponseEntity.accepted().body("Product" + productService.findById(id));
        }

    }

    @PostMapping("/rest/add-product")
    public ResponseEntity<String> addProduct(@Valid @RequestBody Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Something has gone wrong");
        } else {
            productService.addProduct(product);
            return ResponseEntity.accepted().body("Added product");
        }
    }

    @GetMapping("/rest/get-by-name/{name}")
    public List<Product> getProductByName(@PathVariable String name) {
        return productService.findByName(name);
    }

    @GetMapping("/rest/get-by-category/{category}")
    public List<Product> getProductByCategory(@PathVariable String category) {
        return productService.findByCategory(category);
    }

    @PutMapping("/rest/update-price/{id}/{price}")
    public ResponseEntity<String> updateProductPriceById(@PathVariable Long id, @PathVariable BigDecimal price) {
        if (price.signum() < 0) {
            return ResponseEntity.badRequest().body("Price cannot be less than zero");
        } else {
            productService.
        }
    }

}
