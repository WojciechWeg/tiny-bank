package com.tinybank.tinybankapi.services;

import com.tinybank.tinybankapi.modelDAO.AccountDAO;

import java.util.List;


public interface AccountService {

    AccountDAO addAccount(AccountDAO accountDAO);

    List<AccountDAO> getAccountList();

}
