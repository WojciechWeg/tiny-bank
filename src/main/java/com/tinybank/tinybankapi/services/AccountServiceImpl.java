package com.tinybank.tinybankapi.services;

import com.tinybank.tinybankapi.model.Account;
import com.tinybank.tinybankapi.repositories.CustomerRepository;
import org.springframework.stereotype.Service;


public class AccountServiceImpl implements AccountService {

    private final CustomerRepository customerRepository;

    public AccountServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Account createAccount() {
        return null;
    }
}
