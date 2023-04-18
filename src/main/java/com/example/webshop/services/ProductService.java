package com.example.webshop.services;

import com.example.webshop.entitys.Product;
import com.example.webshop.repositorys.ProductRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class ProductService {

    private final ProductRepository productRepository;

    Product product;

    @Autowired
    public ProductService(ProductRepository productRepository) {this.productRepository = productRepository;}


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
