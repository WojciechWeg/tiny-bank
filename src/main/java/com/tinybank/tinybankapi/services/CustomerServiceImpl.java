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
    public List<Customer> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .collect(Collectors.toList());

    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository
                .findById(id)
                .get();
    }

    @Override
    public void deleteCustomerById(Long id) {
         customerRepository.deleteById(id);
    }

    @Override
    public Customer createNewCustomer(Customer customer) {

        return customerRepository.save(customer);
    }

    @Override
    public Customer saveCustomer(Long id, Customer customer) {
        return customerRepository.save(customer);
    }
}
