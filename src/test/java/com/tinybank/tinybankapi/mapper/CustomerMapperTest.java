package com.tinybank.tinybankapi.mapper;

import com.tinybank.tinybankapi.modelDAO.CustomerDAO;
import com.tinybank.tinybankapi.modelDTO.CustomerDTO;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class CustomerMapperTest {

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerDAOToCustomerDTO() {

        //given
        CustomerDAO customerTEST = new CustomerDAO();
        customerTEST.setName("Mirek");
        customerTEST.setSurname("Kowalski");
        customerTEST.setBirthDate(new Date());
        customerTEST.setAddress("Al.Jerozolimskie 1");


        //when
        CustomerDTO customerDTO = customerMapper.customerDAOToCustomerDTO(customerTEST);

        //then

        assertEquals(customerTEST.getName(), customerDTO.getName());
        assertEquals(customerTEST.getSurname(), customerDTO.getSurname());
        assertEquals(customerTEST.getAddress(), customerDTO.getAddress());
        assertEquals(customerTEST.getBirthDate(), customerDTO.getBirthDate());

    }

    @Test
    public void customerDtoToCustomerDAO() {

        //given
        CustomerDTO customerTEST = new CustomerDTO();
        customerTEST.setName("Mirek");
        customerTEST.setSurname("Kowalski");
        customerTEST.setBirthDate(new Date());
        customerTEST.setAddress("Al.Jerozolimskie 1");

        //when
        CustomerDAO customerDAO = customerMapper.customerDtoToCustomerDAO(customerTEST);

        //then
        assertEquals(customerTEST.getName(), customerDAO.getName());
        assertEquals(customerTEST.getSurname(), customerDAO.getSurname());
        assertEquals(customerTEST.getAddress(), customerDAO.getAddress());
        assertEquals(customerTEST.getBirthDate(), customerDAO.getBirthDate());


    }
}