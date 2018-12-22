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

    public static final String BASE_URL = "api/customers";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDAO> getListOfCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDAO getCustomer(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.createNewCustomer(customerDTO);
    }

    @PutMapping({"/{id}/open_account"})
    @ResponseStatus(HttpStatus.OK)
    public void openAccount(@PathVariable Long id, @RequestBody AccountDTO accountDTO) {
        customerService.openAccount(id, accountDTO);
    }

}
