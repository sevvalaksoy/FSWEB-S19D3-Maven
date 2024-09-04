package com.workintech.s19d2.controller;

import com.workintech.s19d2.entity.Account;
import com.workintech.s19d2.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> getAll(){
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account find(@PathVariable long id){
        return accountService.find(id);
    }

    @PostMapping
    public Account post(@RequestBody Account account){
        return accountService.save(account);
    }

    @PutMapping("/{id}")
    public Account update(@PathVariable long id, @RequestBody Account account){
        Account foundAccount = accountService.find(id);
        foundAccount.setName(account.getName());
        accountService.update(id);
        return foundAccount;
    }

    @DeleteMapping("/{id}")
    public Account delete(@PathVariable long id){
        Account account = accountService.find(id);
        if(account!=null){
            accountService.delete(id);
        }
        return account;
    }
}
