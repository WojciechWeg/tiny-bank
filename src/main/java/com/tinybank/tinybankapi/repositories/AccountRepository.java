package com.tinybank.tinybankapi.repositories;

import com.tinybank.tinybankapi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
