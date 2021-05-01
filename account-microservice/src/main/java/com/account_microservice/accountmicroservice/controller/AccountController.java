package com.account_microservice.accountmicroservice.controller;

import com.account_microservice.accountmicroservice.model.Account;
import com.account_microservice.accountmicroservice.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public String test () {
        return "test";
    }

    @PostMapping
    public String save (@RequestBody Account account) {
        return accountService.save(account);
    }

    @GetMapping("/{accountId}")
    public Account getAccountById (@PathVariable String accountId) {
        return accountService.getAccountById(accountId);
    }
}
