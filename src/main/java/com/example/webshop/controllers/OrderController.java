package com.example.webshop.controllers;

import com.example.webshop.entitys.Order;
import com.example.webshop.repositorys.OrderRepository;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Data
@Controller
public class OrderController {
    private final OrderRepository orderRepository;

    @GetMapping("/orders")
    public String getOrderList(Model model) {

        List<Order> orderList = orderRepository.findAll();
        model.addAttribute("orderList", orderList);
        return null;
    }
}
