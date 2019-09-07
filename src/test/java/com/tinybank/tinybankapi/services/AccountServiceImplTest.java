package com.tinybank.tinybankapi.services;

import com.tinybank.tinybankapi.model.Account;
import com.tinybank.tinybankapi.repositories.AccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AccountServiceImplTest {

    AccountService accountService;

    @Mock
    AccountRepository accountRepository;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        accountService = new AccountServiceImpl(accountRepository);

    }

    @Test
    public void addAccount() {

        //given
        List<Account> accounts = Arrays.asList(new Account(), new Account(), new Account());

        //when
        when(accountRepository.findAll()).thenReturn(accounts);

        //then

        assertEquals(3, accounts.size());


    }
}