package com.tinybank.tinybankapi.services;

import com.tinybank.tinybankapi.modelDAO.CustomerDAO;
import com.tinybank.tinybankapi.modelDTO.AccountDTO;
import com.tinybank.tinybankapi.modelDTO.CustomerDTO;

import java.util.List;

public interface CustomerService {


    List<CustomerDAO> getAllCustomers();

    CustomerDAO getCustomerById(Long id);

    void deleteCustomerById(Long id);

    CustomerDAO createNewCustomer(CustomerDTO customerDTO);

    void openAccount(Long id, AccountDTO accountDTO);
}
