package com.tinybank.tinybankapi.repositories;

import com.tinybank.tinybankapi.modelDAO.CustomerDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerDAO,Long> {


}
