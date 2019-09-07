package com.tinybank.tinybankapi.bootstrap;

import com.tinybank.tinybankapi.model.Account;
import com.tinybank.tinybankapi.model.Customer;
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


        Customer customer1 = new Customer("Jan", "Kowalski", new Date(), "Marszalkowska",
                new ArrayList<>());

        customerRepository.save(customer1);

        Customer customer2 = new Customer("Maria", "Kowalska", new Date(), "Marszalkowska",
                new ArrayList<>());

        customerRepository.save(customer2);

        Account account1 = new Account(customer1, "KontoOszczednosciowe");
        Account account2 = new Account(customer1, "KontoInwestycyjne");

        accountRepository.save(account2);
        accountRepository.save(account1);

        List<Account> accountsOfCustomer1 = new ArrayList<>();
        accountsOfCustomer1.add(account1);
        accountsOfCustomer1.add(account2);

        customer1.setAccounts(accountsOfCustomer1);

        System.out.println("Customers loaded.");
    }
}
