package com.tinybank.tinybankapi.services;


import com.tinybank.tinybankapi.model.Customer;
import com.tinybank.tinybankapi.repositories.CustomerRepository;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;
    AccountService accountService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, accountService);
    }

    @Test
    public void getAllCustomers() {

        //given
        Customer customer1 = new Customer();
        customer1.setName("Adam");
        customer1.setSurname("Szef");
        customer1.setAddress("Łódź");
        customer1.setBirthDate(new Date());

        Customer customer2 = new Customer();
        customer2.setName("Krzysiek");
        customer2.setSurname("Vice-Szef");
        customer2.setAddress("Poznań");
        customer2.setBirthDate(new Date());

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        //when
        List<Customer> customers = customerService.getAllCustomers();

        //then
        assertEquals(2, customers.size());
    }

    @Test
    public void getCustomerById() {


        //given
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setName("Jan");
        customer1.setSurname("Nieogar");
        customer1.setAddress("Bumbum");
        customer1.setBirthDate(new Date());

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer1));

        //then

        Customer customer = customerService.getCustomerById(1L);
        assertEquals(customer, customer1);

    }

    @Test
    public void deleteCustomerById() {

        Long id = 1L;
        customerRepository.deleteById(id);

        verify(customerRepository, times(1)).deleteById(anyLong());

    }

    @Test
    public void createNewCustomer() {

        Customer customer1 = new Customer();
        customer1.setName("Johny");
        customer1.setSurname("Bravo");
        customer1.setAddress("Hollywood");
        customer1.setBirthDate(new Date());

        Customer savedCustomer = new Customer();
        savedCustomer.setName(customer1.getName());
        savedCustomer.setSurname(customer1.getSurname());
        savedCustomer.setAddress(customer1.getAddress());
        savedCustomer.setBirthDate(customer1.getBirthDate());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);


        assertEquals(customer1.getName(), savedCustomer.getName());


    }

    @Test
    public void saveCustomer() {

        //given
        Customer Customer = new Customer();
        Customer.setName("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setName(Customer.getName());
        savedCustomer.setSurname(Customer.getSurname());
        savedCustomer.setId(1l);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //then
        assertEquals(Customer.getName(), savedCustomer.getName());

    }
}