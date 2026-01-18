package com.carrental.repository;

import com.carrental.entity.Car;
import com.carrental.config.DatabaseConnection;
import com.carrental.respository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// ПОЛИМОРФИЗМ - CarRepository реализует интерфейс Repository
public class CarRepository implements Repository<Car> {

    // ПОЛИМОРФИЗМ - реализация метода из интерфейса
    @Override
    public void add(Car car) throws SQLException {
        String sql = "INSERT INTO cars (brand, model, year, description, location, price) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, car.getBrand());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getYear());
            stmt.setString(4, car.getDescription());
            stmt.setString(5, car.getLocation());
            stmt.setDouble(6, car.getPrice());

            stmt.executeUpdate();
        }
    }

    @Override
    public List<Car> getAll() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setDescription(rs.getString("description"));
                car.setLocation(rs.getString("location"));
                car.setPrice(rs.getDouble("price"));
                cars.add(car);
            }
        }

        return cars;
    }

    @Override
    public Car getById(int id) throws SQLException {
        String sql = "SELECT * FROM cars WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setDescription(rs.getString("description"));
                car.setLocation(rs.getString("location"));
                car.setPrice(rs.getDouble("price"));
                return car;
            }
        }

        return null;
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM cars WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}