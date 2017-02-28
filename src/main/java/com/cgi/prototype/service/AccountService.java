package com.cgi.prototype.service;

import com.cgi.prototype.domain.Account;
import com.cgi.prototype.repository.AccountRepository;
import com.cgi.prototype.rest.model.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(AccountRequest accountRequest)    {
        Account account = new Account();
        account.setEnterprise(accountRequest.getEnterprise());
        account.setLocale(accountRequest.getLocale());
        account.setMarkedArchived(accountRequest.getMarkedArchived());
        account.setName(accountRequest.getName());
        account.setAccountStatus(accountRequest.getStatus());
        account.setTimeZone(accountRequest.getTimeZone());
        return accountRepository.save(account);
    }

    public List<Account> listAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccount(Long id)  {
        return accountRepository.findOne(id);
    }

}
