package com.example.CustomerService.Controller;

import com.example.CustomerService.DTO.Customer;
import com.example.CustomerService.DTO.ResponseVO;
import com.example.CustomerService.Service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }

    @PostMapping("/createCustomer")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/responseVo/{id}")
    public ResponseVO getResponseVo(@PathVariable long id){
        return customerService.getResponseVo(id);

    }
}
