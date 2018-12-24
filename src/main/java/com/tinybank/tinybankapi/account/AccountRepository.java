package com.tinybank.tinybankapi.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountDAO, Long> {
}
