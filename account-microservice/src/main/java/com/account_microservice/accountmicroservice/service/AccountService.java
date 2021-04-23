package com.account_microservice.accountmicroservice.service;

import com.account_microservice.accountmicroservice.model.Account;
import com.account_microservice.accountmicroservice.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountById (String id) {
        return accountRepository.findById(id).orElseThrow();
    }

    public String save(Account account) {
        account = accountRepository.save(account);
        return account.getId();
    }
}
