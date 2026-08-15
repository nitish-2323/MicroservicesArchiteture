package com.example.CustomerService.DTO;

import lombok.Data;

@Data
public class ResponseVO {
    private Customer customer;
    private AccountPojo accountPojo;
}
