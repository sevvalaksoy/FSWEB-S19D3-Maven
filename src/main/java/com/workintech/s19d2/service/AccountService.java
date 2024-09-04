package com.workintech.s19d2.service;

import com.workintech.s19d2.entity.Account;

import java.util.List;

public interface AccountService {
    Account save(Account account);
    Account update(long id);
    Account delete(long id);
    Account find(long id);
    List<Account> findAll();
}
