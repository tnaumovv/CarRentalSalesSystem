package com.carrental.repository;

import com.carrental.entity.Car;
import com.carrental.config.DatabaseConnection;
import com.carrental.respository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepository implements Repository<Car> {

    @Override
    public void add(Car car) {
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

            stmt.close();
            conn.close();
        }
        catch (SQLException e) {
            System.out.println("Error adding car: " + e.getMessage());
        }
    }

    public void update(Car car) {
        String sql = "UPDATE cars SET brand = ?, model = ?, year = ?, description = ?, location = ?, price = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

             stmt.setString(1, car.getBrand());
             stmt.setString(2, car.getModel());
             stmt.setInt(3, car.getYear());
             stmt.setString(4, car.getDescription());
             stmt.setString(5, car.getLocation());
             stmt.setDouble(6, car.getPrice());
             stmt.setInt(7, car.getId());
             stmt.executeUpdate();

             stmt.close();
             conn.close();
        } catch (SQLException e) {
            System.out.println("Error updating car: " + e.getMessage());
        }
    }

    @Override
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars ORDER BY id";

        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

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

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error getting cars: " + e.getMessage());
        }

        return cars;
    }

    @Override
    public Car getById(int id) {
        String sql = "SELECT * FROM cars WHERE id = ?";
        Car car = null;

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                car = new Car();
                car.setId(rs.getInt("id"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setYear(rs.getInt("year"));
                car.setDescription(rs.getString("description"));
                car.setLocation(rs.getString("location"));
                car.setPrice(rs.getDouble("price"));
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error getting car: " + e.getMessage());
        }

        return car;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM cars WHERE id = ?";

        try {
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error deleting car: " + e.getMessage());

        }
    }
}