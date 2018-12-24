package com.tinybank.tinybankapi.customer;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerDAOToCustomerDTO(CustomerDAO customer);

    CustomerDAO customerDtoToCustomerDAO(CustomerDTO customerDTO);
}
