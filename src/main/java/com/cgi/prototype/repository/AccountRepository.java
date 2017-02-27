package com.cgi.prototype.repository;

import com.cgi.prototype.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
