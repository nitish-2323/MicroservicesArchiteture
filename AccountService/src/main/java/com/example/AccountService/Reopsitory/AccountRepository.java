package com.example.AccountService.Reopsitory;


import com.example.AccountService.AccountDTO.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountNo(String accountNo);
}
