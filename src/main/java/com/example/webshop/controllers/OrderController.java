package com.example.webshop.controllers;

import com.example.webshop.entitys.Order;
import com.example.webshop.repositorys.OrderRepository;
import lombok.Data;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Data
@Controller
public class OrderController {
    private final OrderRepository orderRepository;

//    @GetMapping("/orders")
//    public String getOrderList(Model model, Principal principal) {
//        List<Order> orderList = orderRepository.findAll();
//        boolean isAdmin = false;
//        if (principal != null) {
//            Authentication authentication = (Authentication) principal;
//            isAdmin = authentication.getAuthorities().stream()
//                    .anyMatch(auth -> auth.getAuthority().equals("ADMIN"));
//        }
//        if (isAdmin) {
//            model.addAttribute("orderList", orderList);
//            return "order-list";
//        }
//        return "/";
//    }
}
