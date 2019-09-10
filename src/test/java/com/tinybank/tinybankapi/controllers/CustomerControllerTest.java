package com.tinybank.tinybankapi.controllers;

import com.tinybank.tinybankapi.model.Customer;
import com.tinybank.tinybankapi.services.CustomerService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    Customer customer;

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .build();

        customer = new Customer("Jan","Kowalski",new Date(),"Marszalkowska",new ArrayList<>());
        
    }

    @Test
    public void getListOfCustomers() {
    }

    @Test
    public void getCustomer() throws  Exception {
        when(customerService.getCustomerById(any())).thenReturn(customer);

        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is("Jan")));

    }

    @Test
    public void deleteCustomer() {
    }

    @Test
    public void createNewCustomer() {
    }

    @Test
    public void openAccount() {
    }
}