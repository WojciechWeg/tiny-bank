package com.tinybank.tinybankapi.customer;

import com.tinybank.tinybankapi.account.AccountService;
import com.tinybank.tinybankapi.account.AccountDAO;
import com.tinybank.tinybankapi.account.AccountDTO;
import com.tinybank.tinybankapi.services.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AccountService accountService;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, AccountService accountService) {

        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.accountService = accountService;
    }

    @Override
    public List<CustomerDAO> getAllCustomers() {
        return customerRepository
                .findAll();
    }

    @Override
    public CustomerDAO getCustomerById(Long id) {
        return customerRepository
                .findById(id)
                .orElseThrow(ResourceNotFoundException::new);
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
    public void openAccount(Long id, AccountDTO accountDTO) {
        //stwórz i ustaw obiekt accountDAO
        AccountDAO accountDAO = new AccountDAO();
        accountDAO.setCustomerDAO(getCustomerById(id));
        accountDAO.setDisplayName(accountDTO.getDisplayName());

        //doddaj nowe konto do klienta
        getCustomerById(id).addAccount(accountDAO);

        //zapisz nowe konto w db
        accountService.addAccount(accountDAO);
    }
}
