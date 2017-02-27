package com.cgi.prototype.repository;

import com.cgi.prototype.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
