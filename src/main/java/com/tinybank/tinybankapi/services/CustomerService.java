package com.tinybank.tinybankapi.services;

import com.tinybank.tinybankapi.model.Account;
import com.tinybank.tinybankapi.model.Customer;

import java.util.List;

public interface CustomerService {


    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);

    void deleteCustomerById(Long id);

    Customer createNewCustomer(Customer customer);

    Account openAccount(Long id, Account account);
}
