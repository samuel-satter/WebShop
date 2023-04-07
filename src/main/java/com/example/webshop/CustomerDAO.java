package com.example.webshop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Long> {
    @Override
    Optional<Customer> findById(Long aLong);
}
