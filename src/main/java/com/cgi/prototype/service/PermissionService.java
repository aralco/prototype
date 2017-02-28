package com.cgi.prototype.service;

import com.cgi.prototype.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author aralco
 */
@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;
}
