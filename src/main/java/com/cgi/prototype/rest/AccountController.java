package com.cgi.prototype.rest;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aralco
 */
@Api
@RestController
@RequestMapping("/accounts")
public class AccountController {



    @GetMapping("")
    public ResponseEntity<String> getAccounts() {
        return ResponseEntity.ok("Hi");
    }

}
