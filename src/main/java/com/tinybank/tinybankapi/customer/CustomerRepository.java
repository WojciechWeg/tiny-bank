package com.tinybank.tinybankapi.customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerDAO,Long> {


}
