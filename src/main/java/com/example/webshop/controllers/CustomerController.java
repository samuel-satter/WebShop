package com.example.webshop.controllers;

import com.example.webshop.entitys.Customer;
import com.example.webshop.service.CustomerService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@Controller
public class CustomerController {

    private final CustomerService customerService;
    Customer customer;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/newUser")
    public String getNewUserPage() {
        return "newUser.html";
    }

    @PostMapping("/newUser")
    public String addNewUser(@RequestParam String email, @RequestParam String password, Model model) {
        Customer customer = new Customer();
        customer.setEmailCustomer(email);
        customer.setPasswordCustomer(password);
        customerService.saveCustomer(customer);
        return "index.html";
    }

    @GetMapping("/shop")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        model.addAttribute("customer", customerService.findByEmailAndPassword(email, password));
        if (customerService.isUserAdmin())
            return "admin.html";
        else return "shop.html";
    }


}
