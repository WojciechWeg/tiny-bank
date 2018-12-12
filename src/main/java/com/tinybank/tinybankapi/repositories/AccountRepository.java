package com.tinybank.tinybankapi.repositories;

import com.tinybank.tinybankapi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
