package com.tinybank.tinybankapi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tinybank.tinybankapi.error_handling.ResourceNotFoundException;
import com.tinybank.tinybankapi.error_handling.RestResponseEnityExceptionHandler;
import com.tinybank.tinybankapi.model.Account;
import com.tinybank.tinybankapi.model.Customer;

import com.tinybank.tinybankapi.repositories.CustomerRepository;
import com.tinybank.tinybankapi.services.CustomerService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    Customer customer,customer2,customer3;
    Account account,account2;
    List<Account> accountList;
    List<Customer> customerList;

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .build();

        customer = new Customer("Jan","Kowalski",new Date(),"Marszalkowska",new ArrayList<>());

        account = new Account();
        account.setDisplayName("Oszczednosciowe");

        accountList = new ArrayList<>();
        accountList.add(account);
        customer.setAccounts(accountList);

        customer2 = new Customer("Anna","Nowak",new Date(),"Powsinska",new ArrayList<>());

        customerList = new ArrayList<>();
        customerList.add(customer);
        customerList.add(customer2);

        customer3 = new Customer("Marian","Bela",new Date(),"Lodzka",new ArrayList<>());
        customer3.setId(4L);

        account2 = new Account();
        account2.setDisplayName("Inwestycyjne");

    }

    @Test
    public void getListOfCustomers() throws Exception {

        when(customerService.getAllCustomers()).thenReturn(customerList);

        mockMvc.perform(get("/api/customers"))
                .andExpect(jsonPath("$.content[0].name",Matchers.is(customer.getName())))
                .andExpect(jsonPath("$.content[1].name",Matchers.is(customer2.getName())));
    }

    @Test
    public void getCustomer() throws  Exception {
        when(customerService.getCustomerById(any())).thenReturn(customer);

        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is(customer.getName())))
                .andExpect(jsonPath("$.surname",Matchers.is(customer.getSurname())))
                .andExpect(jsonPath("$.address",Matchers.is(customer.getAddress())))
                .andExpect(jsonPath("$.accounts[0].displayName",Matchers.is(account.getDisplayName())));
    }

    @Test()
    public void getCustomerThatDoesNotExist() throws Exception{

            when(customerService.getCustomerById(any())).thenThrow(ResourceNotFoundException.class);

            mockMvc.perform(get("api/customers/11"))
                    .andExpect(status().isNotFound());


    }

    @Test
    public void deleteCustomer() throws Exception{

        mockMvc.perform(delete("/api/customers/1"))
                .andExpect(status().isOk());

    }

    @Test
    public void createNewCustomer() throws Exception {

        when(customerService.createNewCustomer(any())).thenReturn(customer3);

        mockMvc.perform(
                post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer3)))
                .andExpect(status().isCreated());

    }

    @Test
    public void openAccount() throws Exception {

        String json = "{ \"displayName\": \"Osczednosciowe\"}";

        when(customerService.openAccount(any(),any())).thenReturn(account2);

        mockMvc.perform(
                put("/api/customers/2/open_account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());


    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}