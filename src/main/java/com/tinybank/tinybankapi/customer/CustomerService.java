package com.tinybank.tinybankapi.customer;

import com.tinybank.tinybankapi.account.AccountDTO;

import java.util.List;

public interface CustomerService {


    List<CustomerDAO> getAllCustomers();

    CustomerDAO getCustomerById(Long id);

    void deleteCustomerById(Long id);

    CustomerDAO createNewCustomer(CustomerDTO customerDTO);

    void openAccount(Long id, AccountDTO accountDTO);
}
