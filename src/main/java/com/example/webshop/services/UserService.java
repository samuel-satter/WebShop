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

    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userRepository.findUserByEmailUserAndPasswordUser(email, password);
    }

    public boolean isUserAdmin() {
        return userRepository.isUserAdmin(user.isUserAdmin()).isUserAdmin();
    }

}
