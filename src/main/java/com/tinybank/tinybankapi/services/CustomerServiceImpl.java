package com.tinybank.tinybankapi.services;

import com.tinybank.tinybankapi.mapper.CustomerMapper;
import com.tinybank.tinybankapi.modelDAO.CustomerDAO;
import com.tinybank.tinybankapi.modelDTO.CustomerDTO;
import com.tinybank.tinybankapi.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {

        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDAO> getAllCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .collect(Collectors.toList());

    }

    @Override
    public CustomerDAO getCustomerById(Long id) {
        return customerRepository
                .findById(id)
                .get();
    }

    @Override
    public void deleteCustomerById(Long id) {
         customerRepository.deleteById(id);
    }

    @Override
    public CustomerDAO createNewCustomer(CustomerDTO customerDTO) {

        return customerRepository.save(customerMapper.customerDtoToCustomerDAO(customerDTO));
    }

    @Override
    public CustomerDAO saveCustomer(Long id, CustomerDAO customerDAO) {
        return customerRepository.save(customerDAO);
    }
}
