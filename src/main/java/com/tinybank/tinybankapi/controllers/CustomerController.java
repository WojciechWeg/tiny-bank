package com.tinybank.tinybankapi.controllers;

import com.tinybank.tinybankapi.modelDAO.AccountDAO;
import com.tinybank.tinybankapi.modelDAO.CustomerDAO;
import com.tinybank.tinybankapi.modelDTO.AccountDTO;
import com.tinybank.tinybankapi.modelDTO.CustomerDTO;
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
    public List<CustomerDAO> getListOfCustomers(){
       return customerService.getAllCustomers();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDAO getCustomer(@PathVariable Long id) {return  customerService.getCustomerById(id);}

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void  deleteCustomer(@PathVariable Long id) { customerService.deleteCustomerById(id);}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewCustomer(@RequestBody CustomerDTO customerDTO) {customerService.createNewCustomer(customerDTO);}


    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public  void updateCustomer(@PathVariable Long id, @RequestBody CustomerDAO customerDAO){ customerService.saveCustomer(id, customerDAO);}


    @PutMapping({"/{id}/open_account"})
    @ResponseStatus(HttpStatus.OK)
    public void openAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO){
        //stwórz i ustaw obiekt accountDAO
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.setCustomerDAO(customerService.getCustomerById(id));
        accountDAO.setDisplayName(accountDTO.getDisplayName());

        //doddaj nowe konto do klienta
        customerService.getCustomerById(id).addAccount(accountDAO);

        //zapisz nowe konto w db
        accountService.addAccount(accountDAO);

    }

}
