package com.tinybank.tinybankapi.services;

import com.tinybank.tinybankapi.account.AccountDAO;
import com.tinybank.tinybankapi.account.AccountRepository;
import com.tinybank.tinybankapi.account.AccountService;
import com.tinybank.tinybankapi.account.AccountServiceImpl;
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
        List<AccountDAO> accountDAOS = Arrays.asList(new AccountDAO(), new AccountDAO(), new AccountDAO());

        //when
        when(accountRepository.findAll()).thenReturn(accountDAOS);

        //then

        assertEquals(3, accountDAOS.size());


    }
}