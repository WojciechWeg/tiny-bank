package com.tinybank.tinybankapi.services;

import com.tinybank.tinybankapi.model.Account;
import com.tinybank.tinybankapi.model.Customer;

import java.util.ArrayList;
import java.util.List;

public interface CustomerService {


    Customer createCustomer();

    Account addAccount();

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    void deleteCustomerById(Long id);

    Customer createNewCustomer(Customer customer);

    Customer saveCustomer(Long id, Customer customer);

}