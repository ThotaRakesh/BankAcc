package com.bankapp.dao;

import com.bankapp.models.Transaction;
import java.util.List;

public interface TransactionDAO {
    void addTransaction(Transaction transaction);
    List<Transaction> getTransactionsByAccount(int accountNumber);
    List<Transaction> getAllTransactions();
}
