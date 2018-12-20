package com.tinybank.tinybankapi.mapper;

import com.tinybank.tinybankapi.modelDAO.AccountDAO;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountMapperTest {

    AccountMapper accountMapper = AccountMapper.INSTANCE;


    @Test
    public void accountDAOToAccountDTO() {

        //given
        AccountDAO account = new AccountDAO();


        //when

        //then
    }

    @Test
    public void accountDtoToAccountDAO() {
    }
}