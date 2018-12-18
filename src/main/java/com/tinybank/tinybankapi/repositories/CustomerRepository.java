package com.tinybank.tinybankapi.repositories;

import com.tinybank.tinybankapi.model.Account;
import com.tinybank.tinybankapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {


}
