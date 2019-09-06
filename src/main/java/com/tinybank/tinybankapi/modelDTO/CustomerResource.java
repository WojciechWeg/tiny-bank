package com.tinybank.tinybankapi.modelDTO;

import com.tinybank.tinybankapi.controllers.CustomerController;
import com.tinybank.tinybankapi.modelDAO.CustomerDAO;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CustomerResource extends ResourceSupport {

    private final CustomerDAO customerDAO;

    public CustomerResource(CustomerDAO customerDAO){
        this.customerDAO=customerDAO;
        long id = customerDAO.getId();
        add(linkTo(CustomerController.class).withRel("customers"));
        add(linkTo(methodOn(CustomerController.class).getCustomer(id)).withSelfRel());

    }

    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }
}
