package com.cgi.prototype.rest;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author aralco
 */
@Api
@RestController
@RequestMapping("/users")
public class UserController {
    @PostMapping
    public ResponseEntity<String> createUser() {
        return ResponseEntity.ok("Hi");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("Hi");
    }

    @GetMapping
    public ResponseEntity<String> listUsers() {
        return ResponseEntity.ok("Hi");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser() {
        return ResponseEntity.ok("Hi");
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser() {
        return ResponseEntity.ok("Hi");
    }

}
