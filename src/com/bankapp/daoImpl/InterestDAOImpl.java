package com.bankapp.dao;

import com.bankapp.models.Interest;
import com.bankapp.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InterestDAOImpl implements InterestDAO {
    @Override
    public void addInterest(Interest interest) {
        String sql = "INSERT INTO Interest (account_number, interest_amount, timestamp) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, interest.getAccountNumber());
            stmt.setDouble(2, interest.getInterestAmount());
            stmt.setTimestamp(3, Timestamp.valueOf(interest.getTimestamp()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Interest> getInterestByAccount(int accountNumber) {
        List<Interest> interests = new ArrayList<>();
        String sql = "SELECT * FROM Interest WHERE account_number = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                interests.add(new Interest(
                        rs.getInt("account_number"),
                        rs.getDouble("interest_amount"),
                        rs.getTimestamp("timestamp").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return interests;
    }
}
