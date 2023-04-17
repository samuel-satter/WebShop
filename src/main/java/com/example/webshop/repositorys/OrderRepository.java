package com.example.webshop.repositorys;

import com.example.webshop.entitys.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
