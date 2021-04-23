package com.web_server.webserver.service;

import com.web_server.webserver.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class WebAccountsService {

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;
    protected String serviceUrl;

    public WebAccountsService(String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl : "http://" + serviceUrl;
    }

    public Account getByNumber (String accountNumber) throws AccountNotFoundException {
        Account account = restTemplate.getForObject(serviceUrl + "/api/v1/account/{number}", Account.class, accountNumber);

        if (account == null) {
            throw new AccountNotFoundException(accountNumber);
        } else {
            return account;
        }
    }
}

