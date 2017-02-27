package com.cgi.prototype.repository;

import com.cgi.prototype.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
