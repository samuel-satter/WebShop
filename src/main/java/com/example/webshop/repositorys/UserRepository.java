package com.example.webshop.repositorys;

import com.example.webshop.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long aLong);

    Optional<User> findUserByEmailUserAndPasswordUser(String email, String password);

    User isUserAdmin(boolean isAdmin);


}
