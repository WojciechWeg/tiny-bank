package com.tinybank.tinybankapi.services;

import com.tinybank.tinybankapi.model.Customer;
import org.springframework.stereotype.Service;


public class CustomerServiceImpl implements CustomerService {

    private final CustomerService customerService;

    public CustomerServiceImpl(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public Customer createCustomer() {
        return null;
    }
}
