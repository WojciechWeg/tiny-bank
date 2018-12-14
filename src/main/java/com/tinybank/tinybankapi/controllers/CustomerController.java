package com.tinybank.tinybankapi.controllers;

import com.tinybank.tinybankapi.model.Customer;
import com.tinybank.tinybankapi.services.CustomerService;

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


}
