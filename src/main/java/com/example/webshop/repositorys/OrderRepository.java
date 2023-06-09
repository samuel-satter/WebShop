package com.example.webshop.repositorys;

import com.example.webshop.entitys.Order;
import com.example.webshop.entitys.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}
