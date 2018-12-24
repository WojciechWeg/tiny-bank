package com.tinybank.tinybankapi.account;

import org.springframework.stereotype.Service;

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
