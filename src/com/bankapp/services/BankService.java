package com.bankapp.services;

import com.bankapp.dao.AccountDAO;
import com.bankapp.daoImpl.AccountDAOImpl;
import com.bankapp.models.Account;

import java.util.Optional;

public class BankService {
    private final AccountDAO accountDAO = new AccountDAOImpl();

    public void openAccount(String name, String address, int pin, double initialDeposit, String accountType) {
        Account account = new Account(0, name, address, pin, initialDeposit, accountType);
        accountDAO.createAccount(account);
        System.out.println("Account created successfully!");
    }

    public void deposit(int accountNumber, int pin, double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be positive!");
            return;
        }
        Optional<Account> accountOpt = accountDAO.findAccount(accountNumber);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            if (account.getPin() == pin) {
                account.setBalance(account.getBalance() + amount);
                accountDAO.updateAccount(account);
                System.out.println("Deposit successful!");
            } else {
                System.out.println("Invalid PIN!");
            }
        } else {
            System.out.println("Account not found!");
        }
    }

    public void withdraw(int accountNumber, int pin, double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be positive!");
            return;
        }
        Optional<Account> accountOpt = accountDAO.findAccount(accountNumber);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            if (account.getPin() == pin) {
                if (account.getBalance() >= amount) {
                    account.setBalance(account.getBalance() - amount);
                    accountDAO.updateAccount(account);
                    System.out.println("Withdrawal successful!");
                } else {
                    System.out.println("Insufficient balance!");
                }
            } else {
                System.out.println("Invalid PIN!");
            }
        } else {
            System.out.println("Account not found!");
        }
    }

    public double checkBalance(int accountNumber, int pin) {
        Optional<Account> accountOpt = accountDAO.findAccount(accountNumber);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            if (account.getPin() == pin) {
                return account.getBalance();
            } else {
                System.out.println("Invalid PIN!");
            }
        } else {
            System.out.println("Account not found!");
        }
        return 0;
    }

    public void calculateInterest(int accountNumber, int pin) {
        Optional<Account> accountOpt = accountDAO.findAccount(accountNumber);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            if (account.getPin() == pin) {
                double interestRate = 0.04; // Example: 4% annual interest rate
                double interest = account.getBalance() * interestRate;
                System.out.println("Calculated Interest: " + interest);
            } else {
                System.out.println("Invalid PIN!");
            }
        } else {
            System.out.println("Account not found!");
        }
    }

    public void viewAccountSummary() {
        // Implement logic to fetch and display account summaries by type
    }

    public void viewTransactionSummary() {
        // Implement logic to fetch and display transaction summaries
    }
}
