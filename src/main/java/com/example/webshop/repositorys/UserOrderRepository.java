package com.example.webshop.repositorys;

import com.example.webshop.entitys.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
    @Override
    List<UserOrder> findAll();
}
