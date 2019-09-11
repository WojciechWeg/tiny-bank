package com.tinybank.tinybankapi.services;

import com.tinybank.tinybankapi.error_handling.ResourceNotFoundException;
import com.tinybank.tinybankapi.model.Account;
import com.tinybank.tinybankapi.model.Customer;
import com.tinybank.tinybankapi.repositories.CustomerRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    private final AccountService accountService;

    public CustomerServiceImpl(CustomerRepository customerRepository,  AccountService accountService) {

        this.customerRepository = customerRepository;
        this.accountService = accountService;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository
                .findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteCustomerById(Long id) {
        try {
        customerRepository.deleteById(id);}
        catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public Customer createNewCustomer(Customer customer) {
        customer.setAccounts(new LinkedList<>());
        return customerRepository.save(customer);
    }

    @Override
    public Account openAccount(Long id, Account Account) {
        //stwórz i ustaw obiekt Account
        Account account = new Account();
        account.setCustomer(getCustomerById(id));
        account.setDisplayName(Account.getDisplayName());

        //doddaj nowe konto do klienta
        getCustomerById(id).addAccount(account);

        //zapisz nowe konto w db
        accountService.addAccount(account);

        return account;
    }
}
