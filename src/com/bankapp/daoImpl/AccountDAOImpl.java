package com.bankapp.daoImpl;

import com.bankapp.dao.AccountDAO;
import com.bankapp.models.Account;
import com.bankapp.utils.DBConnection;
import java.sql.*;
import java.util.Optional;
import java.util.Random;

public class AccountDAOImpl implements AccountDAO {
    @Override
    public void createAccount(Account account) {
        String insertQueary = "INSERT INTO Account (account_number, name, address, pin, balance, account_type) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQueary)) {
            int accountNumber = generateAccountNumber(); // Generate account number
            while (!isAccountNumberUnique(accountNumber)) { // Ensure uniqueness
                accountNumber = generateAccountNumber();
            }

            stmt.setInt(1, accountNumber);
            stmt.setString(2, account.getName());
            stmt.setString(3, account.getAddress());
            stmt.setInt(4, account.getPin());
            stmt.setDouble(5, account.getBalance());
            stmt.setString(6, account.getAccountType());


            stmt.executeUpdate();
            System.out.println("Account created successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private int generateAccountNumber() {
        // Example: Generate a random 10-digit account number
        return 1000000000 + new Random().nextInt(900000000);
    }

    private boolean isAccountNumberUnique(int accountNumber) {
        String uniqueAccQueary = "SELECT 1 FROM Account WHERE account_number = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(uniqueAccQueary)) {

            stmt.setInt(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            return !rs.next(); // If no record found, account number is unique

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Optional<Account> findAccount(int accountNumber) {
        String query = "SELECT * FROM Account WHERE account_number = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Account account = new Account(
                        rs.getInt("account_number"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getInt("pin"),
                        rs.getDouble("balance"),
                        rs.getString("account_type")
                );
                return Optional.of(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean updateAccount(Account account) {
        String query = "UPDATE Account SET balance = ? WHERE account_number = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, account.getBalance());
            stmt.setInt(2, account.getAccountNumber());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteAccount(int accountNumber) {
        String query = "DELETE FROM Account WHERE account_number = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, accountNumber);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
