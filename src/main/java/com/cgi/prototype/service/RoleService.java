package com.cgi.prototype.service;

import com.cgi.prototype.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author aralco
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;
}
