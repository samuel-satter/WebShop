package com.example.webshop.repositorys;

import com.example.webshop.entitys.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Override
    Optional<Customer> findById(Long aLong);

    Optional<Customer> findCustomerByEmailCustomerAndPasswordCustomer(String email, String password);

    Customer isUserAdmin(boolean isAdmin);


}
