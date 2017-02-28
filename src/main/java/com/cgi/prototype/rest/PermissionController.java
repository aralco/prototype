package com.cgi.prototype.rest;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author aralco
 */
@Api
@RestController
@RequestMapping("/permissions")
public class PermissionController {
    @PostMapping
    public ResponseEntity<String> createPermission() {
        return ResponseEntity.ok("Hi");
    }

    @GetMapping("/{permissionId}")
    public ResponseEntity<String> getPermission() {
        return ResponseEntity.ok("Hi");
    }

    @GetMapping
    public ResponseEntity<String> listPermissions() {
        return ResponseEntity.ok("Hi");
    }

    @PutMapping("/{permissionId}")
    public ResponseEntity<String> updatePermission() {
        return ResponseEntity.ok("Hi");
    }

    @DeleteMapping("/{permissionId}")
    public ResponseEntity<String> deletePermission() {
        return ResponseEntity.ok("Hi");
    }

}
