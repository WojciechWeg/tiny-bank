package com.tinybank.tinybankapi.bootstrap;

import com.tinybank.tinybankapi.modelDAO.AccountDAO;
import com.tinybank.tinybankapi.modelDAO.CustomerDAO;
import com.tinybank.tinybankapi.repositories.AccountRepository;
import com.tinybank.tinybankapi.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public Bootstrap(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void run(String... args) throws Exception {



        CustomerDAO customerDAO1 = new CustomerDAO("Jan","Kowalski", new Date(),"Marszalkowska",
                new ArrayList<>());

        customerRepository.save(customerDAO1);

        CustomerDAO customerDAO2 = new CustomerDAO("Maria","Kowalska", new Date(),"Marszalkowska",
                new ArrayList<>());

        customerRepository.save(customerDAO2);

        AccountDAO accountDAO1 = new AccountDAO(customerDAO1,"KontoOszczednosciowe");
        AccountDAO accountDAO2 = new AccountDAO(customerDAO1,"KontoInwestycyjne");

        accountRepository.save(accountDAO2);
        accountRepository.save(accountDAO1);

        List<AccountDAO> accountsOfCustomer1 = new ArrayList<>();
        accountsOfCustomer1.add(accountDAO1);
        accountsOfCustomer1.add(accountDAO2);

        customerDAO1.setAccountDAOS(accountsOfCustomer1);

        System.out.println("Customers loaded.");
    }
}
