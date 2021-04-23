package com.account_microservice.accountmicroservice.repository;

import com.account_microservice.accountmicroservice.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
}
