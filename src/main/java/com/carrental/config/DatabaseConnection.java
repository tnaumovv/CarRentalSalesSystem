package com.carrental.config;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/CarRentalSale";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1212";  // Укажите свой пароль

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}


