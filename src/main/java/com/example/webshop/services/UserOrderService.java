package com.example.webshop.services;

import com.example.webshop.entitys.UserOrder;
import com.example.webshop.repositorys.UserOrderRepository;
import com.example.webshop.repositorys.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class UserOrderService {

    private final UserOrderRepository userOrderRepository;

    @Autowired
    public UserOrderService(UserOrderRepository userOrderRepository) {
        this.userOrderRepository = userOrderRepository;
    }
    public List<UserOrder> getAllUserOrders() {
        return userOrderRepository.findAll();
    }
}
