package com.example.webshop.services;

import com.example.webshop.entitys.Product;
import com.example.webshop.entitys.User;
import com.example.webshop.repositorys.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

@Service
@SessionScope
public class UserService {
    private final UserRepository userRepository;

    User user;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

     public Optional<User> findByUsername(String username) {
         return userRepository.findByUsername(username);
     }

    public User authenticateUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }


    public boolean isUserAdmin(User user) {
        return user.isAdmin();
    }

}
