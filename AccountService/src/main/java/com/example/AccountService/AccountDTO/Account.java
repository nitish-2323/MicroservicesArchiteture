package com.example.AccountService.AccountDTO;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "accountMicroservice")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountNo;

    private double balance;

}
