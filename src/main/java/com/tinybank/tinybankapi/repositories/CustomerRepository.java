package com.tinybank.tinybankapi.repositories;

import com.tinybank.tinybankapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {


}
