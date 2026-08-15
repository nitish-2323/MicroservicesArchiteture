package com.example.CustomerService.Repositry;

import com.example.CustomerService.DTO.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
