package com.tinybank.tinybankapi.controllers;

import com.tinybank.tinybankapi.model.Customer;
import com.tinybank.tinybankapi.services.CustomerService;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public static final  String BASE_URL = "api/customers";

    private final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
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
    public  void updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
         customerService.saveCustomer(id, customer);}

}
