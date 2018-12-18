package com.tinybank.tinybankapi.controllers;

import com.tinybank.tinybankapi.model.Account;
import com.tinybank.tinybankapi.model.Customer;
import com.tinybank.tinybankapi.services.AccountService;
import com.tinybank.tinybankapi.services.CustomerService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final  String BASE_URL = "api/customers";

    private final CustomerService customerService;
    private final AccountService accountService;

    public CustomerController(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getListOfCustomers(){
       return customerService.getAllCustomers();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public  Customer getCustomer(@PathVariable Long id) {return  customerService.getCustomerById(id);}

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void  deleteCustomer(@PathVariable Long id) { customerService.deleteCustomerById(id);}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewCustomer(@RequestBody Customer customer) {customerService.createNewCustomer(customer);}


    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public  void updateCustomer(@PathVariable Long id, @RequestBody Customer customer){ customerService.saveCustomer(id, customer);}


    @PutMapping({"/{id}/open_account"})
    @ResponseStatus(HttpStatus.OK)
    public void openAccount(@PathVariable Long id, @RequestBody Account account){
        account.setCustomer(customerService.getCustomerById(id));
        customerService.getCustomerById(id).addAccount(account);
        accountService.addAccount(account);

    }

}
