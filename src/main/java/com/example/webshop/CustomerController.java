package com.example.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;
}
