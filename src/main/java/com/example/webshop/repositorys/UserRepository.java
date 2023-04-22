package com.example.webshop.repositorys;

import com.example.webshop.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long aLong);

    @Override
    <S extends User> S save(S entity);

    Optional<User> findByUsername(String username);

}
