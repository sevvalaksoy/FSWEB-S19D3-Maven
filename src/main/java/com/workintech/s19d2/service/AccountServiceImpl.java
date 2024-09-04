package com.workintech.s19d2.service;

import com.workintech.s19d2.repository.AccountRepository;
import com.workintech.s19d2.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account update(long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if(optionalAccount.isPresent()){
            return accountRepository.save(optionalAccount.get());
        }
        //throw exception
        return null;
    }

    @Override
    public Account delete(long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if(optionalAccount.isPresent()){
            Account account = optionalAccount.get();
            accountRepository.delete(account);
            return account;
        }
        //throw exception
        return null;
    }

    @Override
    public Account find(long id) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if(optionalAccount.isPresent()){
            return optionalAccount.get();
        }
        //throw exception
        return null;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }
}
