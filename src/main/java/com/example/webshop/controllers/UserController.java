package com.example.webshop.controllers;

import com.example.webshop.entitys.User;
import com.example.webshop.services.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@Controller
public class UserController {

    private final UserService userService;
    User user;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/newUser")
    public String getNewUserPage() {
        return "newUser.html";
    }

    @PostMapping("/newUser")
    public String addNewUser(@RequestParam String email, @RequestParam String password) {
        User user = new User();
        user.setEmailUser(email);
        user.setPasswordUser(password);
        userService.saveUser(user);
        return "index.html";
    }

    @GetMapping("/shop")
    public String login(@RequestParam String email, @RequestParam String password, Model model) {
        model.addAttribute("user", userService.findByEmailAndPassword(email, password));
        if (userService.isUserAdmin())
            return "admin.html";
        else return "shop.html";
    }


}
