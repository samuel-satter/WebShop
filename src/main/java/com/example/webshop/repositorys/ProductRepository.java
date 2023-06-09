package com.example.webshop.repositorys;

import com.example.webshop.entitys.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    <S extends Product> S save(S entity);

    @Override
    List<Product> findAll();

    List<Product> findByProductNameIgnoreCase(String productName);

    List<Product> findByProductCategory(String productCategory);

    List<Product> findByProductNameContainingIgnoreCaseAndProductCategory(String productName, String productCategory);

    @Query("SELECT DISTINCT p.productCategory FROM Product p")
    List<String> findAllProductCategories();

}

