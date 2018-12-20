package com.tinybank.tinybankapi.repositories;

import com.tinybank.tinybankapi.modelDAO.AccountDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountDAO, Long> {
}
