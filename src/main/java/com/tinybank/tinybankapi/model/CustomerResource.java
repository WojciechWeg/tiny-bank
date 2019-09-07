package com.tinybank.tinybankapi.model;

import com.tinybank.tinybankapi.controllers.CustomerController;
import com.tinybank.tinybankapi.model.Customer;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CustomerResource extends ResourceSupport {

    private final Customer customer;

    public CustomerResource(Customer customer){
        this.customer = customer;
        long id = customer.getId();
        add(linkTo(CustomerController.class).withRel("customers"));
        add(linkTo(methodOn(CustomerController.class).getCustomer(id)).withSelfRel());

    }

    public Customer getCustomer() {
        return customer;
    }
}
