package com.example.webshop.controllers;

import com.example.webshop.entitys.User;
import com.example.webshop.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

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
        user.setUsername(email);
        user.setPassword(password);
        userService.saveUser(user);
        return "index.html";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        Optional<User> user = userService.tryFindingByUsernameAndPassword(username, password);
        if (user.isPresent()) {
            session.setAttribute("user", user);
            if (userService.isUserAdmin()) {
                return "admin.html";
            } else {
                return "shop.html";
            }
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login.html";
        }
    }

}
