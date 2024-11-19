package com.bankapp.dao;

import com.bankapp.models.Account;
import java.util.Optional;

public interface AccountDAO {
    void createAccount(Account account);
    Optional<Account> findAccount(int accountNumber);
    boolean updateAccount(Account account);
    boolean deleteAccount(int accountNumber);
}
