package com.example.CustomerService.Service;

import com.example.CustomerService.DTO.AccountPojo;
import com.example.CustomerService.DTO.Customer;
import com.example.CustomerService.DTO.ResponseVO;
import com.example.CustomerService.Repositry.CustomerRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CustomerService  {
    private final CustomerRepository customerRepository;
    @Autowired
    private RestTemplate restTemplate;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }
     @CircuitBreaker(
             name ="AccountService",
             fallbackMethod = "accountServiceFallback"
     )
    public ResponseVO getResponseVo(long id) {
        Customer customer =customerRepository.findById(id).orElseThrow(null);
        AccountPojo accountPojo =restTemplate.getForObject("http://AccountService/accounts/"+customer.getId()
                ,AccountPojo.class);
        ResponseVO responseVO = new ResponseVO();
        responseVO.setCustomer(customer);
        responseVO.setAccountPojo(accountPojo);
        return responseVO;
    }
    public ResponseVO accountServiceFallback(long id, Exception e) {

        ResponseVO responseVO = new ResponseVO();

        responseVO.setCustomer(
                customerRepository.findById(id).orElse(null)
        );

        responseVO.setAccountPojo(null);

        return responseVO;
    }
}
