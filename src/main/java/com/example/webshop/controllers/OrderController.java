package com.example.webshop.controllers;

import com.example.webshop.entitys.Order;
import com.example.webshop.repositorys.OrderRepository;
import com.example.webshop.services.OrderService;
import lombok.Data;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Data
@Controller
public class OrderController {
    private final OrderService orderService;


}
