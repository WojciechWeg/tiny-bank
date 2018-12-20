package com.tinybank.tinybankapi.mapper;

import com.tinybank.tinybankapi.modelDAO.CustomerDAO;
import com.tinybank.tinybankapi.modelDTO.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerDAOToCustomerDTO(CustomerDAO customer);

    CustomerDAO customerDtoToCustomerDAO(CustomerDTO customerDTO);
}
