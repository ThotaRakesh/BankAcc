package com.bankapp;

import com.bankapp.services.BankService;

import java.util.Scanner;

class MainApp {
    public static void main(String[] args) {
        BankService bankService = new BankService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Bank Application ---");
            System.out.println("1. Add/Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Calculate Interest");
            System.out.println("6. View Account Summary and Reports");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter Name: ");

                    String name = scanner.next();
                    System.out.println("Enter Address: ");
                    String address = scanner.next();
                    System.out.println("Set 4-digit PIN: ");
                    int pin = scanner.nextInt();
                    System.out.println("Enter Initial Deposit (minimum 1000): ");
                    double initialDeposit = scanner.nextDouble();
                    System.out.println("Enter Account Type (Saving/Checking): ");
                    String accountType = scanner.next();
                    if (initialDeposit >= 1000) {
                        bankService.openAccount(name, address, pin, initialDeposit, accountType);
                    } else {
                        System.out.println("Initial deposit must be at least 1000!");
                    }
                    break;

                case 2:
                    System.out.println("Enter Account Number: ");
                    int depositAccountNumber = scanner.nextInt();
                    System.out.println("Enter PIN: ");
                    int depositPin = scanner.nextInt();
                    System.out.println("Enter Amount to Deposit: ");
                    double depositAmount = scanner.nextDouble();
                    bankService.deposit(depositAccountNumber, depositPin, depositAmount);
                    break;

                case 3:
                    System.out.println("Enter Account Number: ");
                    int withdrawAccountNumber = scanner.nextInt();
                    System.out.println("Enter PIN: ");
                    int withdrawPin = scanner.nextInt();
                    System.out.println("Enter Amount to Withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    bankService.withdraw(withdrawAccountNumber, withdrawPin, withdrawAmount);
                    break;

                case 4:
                    System.out.println("Enter Account Number: ");
                    int balanceAccountNumber = scanner.nextInt();
                    System.out.println("Enter PIN: ");
                    int balancePin = scanner.nextInt();
                    double balance = bankService.checkBalance(balanceAccountNumber, balancePin);
                    System.out.println("Your Balance: " + balance);
                    break;

                case 5:
                    System.out.println("Enter Account Number: ");
                    int interestAccountNumber = scanner.nextInt();
                    System.out.println("Enter PIN: ");
                    int interestPin = scanner.nextInt();
                    bankService.calculateInterest(interestAccountNumber, interestPin);
                    break;

                case 6:
                    System.out.println("--- Account Summary and Reports ---");
                    System.out.println("1. Total Accounts by Type");
                    System.out.println("2. Total Deposits and Withdrawals");
                    System.out.print("Enter your choice: ");
                    int reportChoice = scanner.nextInt();
                    if (reportChoice == 1) {
                        bankService.viewAccountSummary();
                    } else if (reportChoice == 2) {
                        bankService.viewTransactionSummary();
                    } else {
                        System.out.println("Invalid choice!");
                    }
                    break;

                case 7:
                    System.out.println("Exiting Application. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
