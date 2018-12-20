package com.tinybank.tinybankapi.mapper;

import com.tinybank.tinybankapi.modelDAO.AccountDAO;
import com.tinybank.tinybankapi.modelDAO.CustomerDAO;
import com.tinybank.tinybankapi.modelDTO.AccountDTO;
import com.tinybank.tinybankapi.modelDTO.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

   AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDTO accountDAOToAccountDTO(AccountDAO account);

    AccountDAO accountDtoToAccountDAO(AccountDTO accountDTO);
}
