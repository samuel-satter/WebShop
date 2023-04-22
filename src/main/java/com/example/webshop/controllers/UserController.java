package com.example.webshop.controllers;

import com.example.webshop.entitys.Product;
import com.example.webshop.entitys.User;
import com.example.webshop.services.ProductService;
import com.example.webshop.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Data
@Controller
public class UserController {

    private final UserService userService;
    User user;

    private final ProductService productService;

    @Autowired
    public UserController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/newUser")
    public String getNewUserPage() {
        return "newUser.html";
    }

    @PostMapping("/newUser")
    public String addNewUser(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.saveUser(user);
        return "index.html";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        User user = userService.authenticateUser(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            if (userService.isUserAdmin(user)) {
                return "admin.html";
            } else {
                List<Product> productList = productService.getAllProducts();
                model.addAttribute("categoryList", productList.stream().map(Product::getProductCategory)
                        .distinct()
                        .toList());
                model.addAttribute("selectedCategory", "All Categories");
                session.setAttribute("username", username);
                return "shop.html";
            }
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "index.html";
        }
    }


}
