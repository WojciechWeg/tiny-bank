package com.tinybank.tinybankapi.services;

import com.tinybank.tinybankapi.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AccountService {

    Account addAccount(Account account);

    List<Account> getAccountList();

}
