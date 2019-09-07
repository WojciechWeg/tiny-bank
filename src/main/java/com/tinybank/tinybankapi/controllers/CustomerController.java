package com.tinybank.tinybankapi.controllers;

import com.tinybank.tinybankapi.model.Account;
import com.tinybank.tinybankapi.model.Customer;
import com.tinybank.tinybankapi.model.CustomerResource;
import com.tinybank.tinybankapi.services.CustomerService;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<Resources<List<Customer>>> getListOfCustomers() {
        Resources<List<Customer>> resources = new Resources(customerService.getAllCustomers());
        String uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUriString();
        resources.add(new Link(uri,"self"));
        return ResponseEntity.ok(resources);
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CustomerResource> createNewCustomer(@RequestBody Customer Customer) {


        Customer customer = customerService.createNewCustomer(Customer);
        URI uri = MvcUriComponentsBuilder.fromController(getClass())
                .path("/{id}")
                .buildAndExpand(customer.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new CustomerResource(customer));
    }

    @PutMapping({"/{id}/open_account"})
    @ResponseStatus(HttpStatus.OK)
    public void openAccount(@PathVariable Long id, @RequestBody Account account) {
        customerService.openAccount(id, account);
    }

}
