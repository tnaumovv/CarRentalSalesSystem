package com.carrental.respository;
import com.carrental.entity.User;
import com.carrental.config.DatabaseConnection;

import java.sql.*;


public class UserRepository {
    public void register(User user) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            stmt.executeUpdate();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
        }
    }

    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        User user = null;

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error logging in: " + e.getMessage());
        }

        return user;
    }

    public boolean userExists(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                boolean exists = rs.getInt(1) > 0;
                rs.close();
                stmt.close();
                conn.close();
                return exists;
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error checking user: " + e.getMessage());
        }

        return false;
    }
}
