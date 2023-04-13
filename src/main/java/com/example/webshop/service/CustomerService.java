package com.example.webshop.service;

import com.example.webshop.entitys.Customer;
import com.example.webshop.repositorys.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepo;

    @Autowired
    public CustomerService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public void saveCustomer(Customer customer) {
        customerRepo.save(customer);
    }

    public Optional<Customer> findByEmailAndPassword(String email, String password) {
        return customerRepo.findCustomerByEmailCustomerAndPasswordCustomer(email, password);
    }
}
