package com.tinybank.tinybankapi.services;

import com.tinybank.tinybankapi.model.Account;
import com.tinybank.tinybankapi.model.Customer;
import com.tinybank.tinybankapi.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {

        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer() {
        return null;
    }

    @Override
    public Account addAccount() {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .collect(Collectors.toList());

    }


}
