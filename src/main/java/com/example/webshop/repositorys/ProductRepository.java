package com.example.webshop.repositorys;

import com.example.webshop.entitys.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    <S extends Product> S save(S entity);

    @Override
    List<Product> findAll();
}
