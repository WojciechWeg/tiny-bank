package com.tinybank.tinybankapi.services;

import com.tinybank.tinybankapi.account.AccountService;
import com.tinybank.tinybankapi.customer.*;
import com.tinybank.tinybankapi.customer.CustomerMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    CustomerService customerService;
    AccountService accountService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(customerRepository, customerMapper, accountService);
    }

    @Test
    public void getAllCustomers() {

        //given
        CustomerDAO customer1 = new CustomerDAO();
        customer1.setName("Adam");
        customer1.setSurname("Szef");
        customer1.setAddress("Łódź");
        customer1.setBirthDate(new Date());

        CustomerDAO customer2 = new CustomerDAO();
        customer2.setName("Krzysiek");
        customer2.setSurname("Vice-Szef");
        customer2.setAddress("Poznań");
        customer2.setBirthDate(new Date());

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        //when
        List<CustomerDAO> customerDAOS = customerService.getAllCustomers();

        //then
        assertEquals(2, customerDAOS.size());
    }

    @Test
    public void getCustomerById() {


        //given
        CustomerDAO customer1 = new CustomerDAO();
        customer1.setId(1L);
        customer1.setName("Jan");
        customer1.setSurname("Nieogar");
        customer1.setAddress("Bumbum");
        customer1.setBirthDate(new Date());

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer1));

        //then

        CustomerDAO customerDAO = customerService.getCustomerById(1L);
        assertEquals(customerDAO, customer1);

    }

    @Test
    public void deleteCustomerById() {

        Long id = 1L;
        customerRepository.deleteById(id);

        verify(customerRepository, times(1)).deleteById(anyLong());

    }

    @Test
    public void createNewCustomer() {

        CustomerDTO customer1 = new CustomerDTO();
        customer1.setName("Johny");
        customer1.setSurname("Bravo");
        customer1.setAddress("Hollywood");
        customer1.setBirthDate(new Date());

        CustomerDAO savedCustomer = new CustomerDAO();
        savedCustomer.setName(customer1.getName());
        savedCustomer.setSurname(customer1.getSurname());
        savedCustomer.setAddress(customer1.getAddress());
        savedCustomer.setBirthDate(customer1.getBirthDate());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(CustomerDAO.class))).thenReturn(savedCustomer);


        assertEquals(customer1.getName(), savedCustomer.getName());


    }

    @Test
    public void saveCustomer() {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Jim");

        CustomerDAO savedCustomer = new CustomerDAO();
        savedCustomer.setName(customerDTO.getName());
        savedCustomer.setSurname(customerDTO.getSurname());
        savedCustomer.setId(1l);

        when(customerRepository.save(any(CustomerDAO.class))).thenReturn(savedCustomer);

        //then
        assertEquals(customerDTO.getName(), savedCustomer.getName());

    }
}