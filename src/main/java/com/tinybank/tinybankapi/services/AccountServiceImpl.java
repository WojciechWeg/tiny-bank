package com.tinybank.tinybankapi.services;

import com.tinybank.tinybankapi.modelDAO.AccountDAO;
import com.tinybank.tinybankapi.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDAO addAccount(AccountDAO accountDAO) {
        return accountRepository.save(accountDAO);
    }

}
