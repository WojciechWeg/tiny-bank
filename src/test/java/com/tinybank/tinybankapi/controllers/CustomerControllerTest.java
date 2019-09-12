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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@AutoConfigureRestDocs()
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

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .apply(documentationConfiguration(this.restDocumentation))
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

        customer3 = new Customer("Marian","Bela",new Date(),"Lodzka");
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

        mockMvc.perform(get("/api/customers/{id}",1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is(customer.getName())))
                .andExpect(jsonPath("$.surname",Matchers.is(customer.getSurname())))
                .andExpect(jsonPath("$.address",Matchers.is(customer.getAddress())))
                .andExpect(jsonPath("$.accounts[0].displayName",Matchers.is(account.getDisplayName())))
                .andDo(document("/api/customers-get",
                        pathParameters(
                                parameterWithName("id").description("Customers id")
                        ),
                        responseFields(
                                fieldWithPath("name").description("Customer name"),
                                fieldWithPath("surname").description("Customer surname"),
                                fieldWithPath("birthDate").description("Customer birth date"),
                                fieldWithPath("address").description("Customer address"),
                                fieldWithPath("accounts[]").description("List of customer's accounts"),
                                fieldWithPath("accounts[].displayName").description("Name of account")
                        )
                        ));
    }

    @Test()
    public void getCustomerThatDoesNotExist() throws Exception{

            when(customerService.getCustomerById(any())).thenThrow(ResourceNotFoundException.class);

            mockMvc.perform(get("api/customers/11"))
                    .andExpect(status().isNotFound());

    }

    @Test
    public void deleteCustomer() throws Exception{

        mockMvc.perform(delete("/api/customers/{id}",1))
                .andExpect(status().isOk())
                .andDo(document("api/customer-delete",
                        pathParameters(
                                parameterWithName("id").description("Customers id to be deleted")
                        )
                ));

    }

    @Test
    public void createNewCustomer() throws Exception {

        when(customerService.createNewCustomer(any())).thenReturn(customer3);

        ConstrainedFields fields = new ConstrainedFields(Customer.class);

        mockMvc.perform(
                post("/api/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer3)))
                .andExpect(status().isCreated())
                .andDo(document("api/customers-new",
                        requestFields(
                                fields.withPath("name").description("Customer name"),
                                fields.withPath("surname").description("Customer surname"),
                                fields.withPath("birthDate").description("Customer birth date"),
                                fields.withPath("address").description("Customer address"),
                                fields.withPath("accounts[]").ignored()
                        )));

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

    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}