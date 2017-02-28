package com.cgi.prototype.rest;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author aralco
 */
@Api
@RestController
@RequestMapping("/roles")
public class RoleController {
    @PostMapping
    public ResponseEntity<String> createRole() {
        return ResponseEntity.ok("Hi");
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<String> getRole() {
        return ResponseEntity.ok("Hi");
    }

    @GetMapping
    public ResponseEntity<String> listRoles() {
        return ResponseEntity.ok("Hi");
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<String> updateRole() {
        return ResponseEntity.ok("Hi");
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<String> deleteRole() {
        return ResponseEntity.ok("Hi");
    }

}
