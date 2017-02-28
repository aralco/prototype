package com.cgi.prototype.rest;

import com.cgi.prototype.domain.Account;
import com.cgi.prototype.rest.model.AccountRequest;
import com.cgi.prototype.service.AccountService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest accountRequest) {
        return ResponseEntity.ok(accountService.createAccount(accountRequest));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccount(@PathVariable Long accountId) {
        return ResponseEntity.ok(accountService.getAccount(accountId));
    }

    @GetMapping
    public ResponseEntity<List<Account>> listAccounts() {
        return ResponseEntity.ok(accountService.listAccounts());
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<String> updateAccount() {
        return ResponseEntity.ok("Hi");
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount() {
        return ResponseEntity.ok("Hi");
    }

}
