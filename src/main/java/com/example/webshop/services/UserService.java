package com.example.webshop.services;

import com.example.webshop.entitys.User;
import com.example.webshop.repositorys.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
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

    public Optional<User> authenticateUser(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (password.equals(user.getPassword())) {
                return optionalUser;
            }
        }
        return Optional.empty();
    }

    public boolean isUserAdmin() {
        if (user == null) {
            return false;
        }
        return userRepository.isUserAdmin(user.isUserAdmin()).isUserAdmin();
    }

}
