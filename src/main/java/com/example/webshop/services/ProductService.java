package com.example.webshop.services;

import com.example.webshop.entitys.Product;
import com.example.webshop.repositorys.ProductRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Data
@Service
@SessionScope
public class ProductService {

    private final ProductRepository productRepository;

    Product product;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> searchForProducts(String productName, String selectedCategory) {
        if (selectedCategory == null || selectedCategory.trim().isEmpty()) {
            return productRepository.findByProductNameIgnoreCase(productName);
        } else {
            return productRepository.findByProductNameContainingIgnoreCaseAndProductCategory(productName, selectedCategory);
        }
    }
    //    public List<Product> searchForProducts(String productName, String productCategory) {
//        return productRepository.findByproductNameContainingIgnoreCaseAndAndProductCategory(productName, productCategory);
//    }
    public List<String> getAllProductCategories() {
        return productRepository.findAllProductCategories();
    }

}
