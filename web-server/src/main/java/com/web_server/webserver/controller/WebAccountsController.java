package com.web_server.webserver.controller;

import com.web_server.webserver.model.Account;
import com.web_server.webserver.service.WebAccountsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class WebAccountsController {

    private final WebAccountsService accountsService;

    public WebAccountsController(WebAccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @GetMapping
    public String hello () {
        return "null";
    }

    @GetMapping("/{accountNumber}")
    public Account hello (@PathVariable String accountNumber) {
        try {
            return this.accountsService.getByNumber(accountNumber);
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
