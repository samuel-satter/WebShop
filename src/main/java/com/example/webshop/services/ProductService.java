package com.example.webshop.services;

import com.example.webshop.entitys.Product;
import com.example.webshop.repositorys.ProductRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Data
@Service
@SessionScope
public class ProductService {

    Product product;

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid product"));
    }

    public List<Product> findByName(String name) {
        return productRepository.findByProductNameIgnoreCase(name);
    }

    public List<Product> findByCategory(String category) {
        return productRepository.findByProductCategory(category);
    }


    public List<String> getAllProductCategories() {
        return productRepository.findAllProductCategories();
    }

    public List<Product> searchForProducts(String productName, String selectedCategory) {
        boolean categoryExists = selectedCategory != null && !selectedCategory.isEmpty();
        boolean productNameExists = productName != null && !productName.isEmpty();
        if (categoryExists && productNameExists){
            return productRepository.findByProductNameContainingIgnoreCaseAndProductCategory(productName, selectedCategory);
        } else if (!categoryExists && !productNameExists) {
            return productRepository.findAll();
        } else if (categoryExists) {
            return productRepository.findByProductCategory(selectedCategory);
        } else {
            return productRepository.findByProductNameIgnoreCase(productName);
        }
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProductPrice(Long id, BigDecimal price){
        Product updatedProduct = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Could not find product"));
        updatedProduct.setProductPrice(price);
    }

}
