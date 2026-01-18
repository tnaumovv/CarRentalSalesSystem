package com.carrental.service;

import com.carrental.entity.Car;
import com.carrental.repository.CarRepository;
import com.carrental.respository.Repository;

import java.sql.SQLException;
import java.util.List;

public class CarService extends BaseService<Car> {
    private Repository<Car> repository;

    public CarService() {
        this.repository = new CarRepository();
    }

    @Override
    public void add(Car car) throws SQLException {
        validateYear(car.getYear());
        validatePrice(car.getPrice());
        repository.add(car);
    }

    @Override
    public List<Car> getAll() throws SQLException {
        return repository.getAll();
    }

    @Override
    public Car getById(int id) throws SQLException {
        Car car = repository.getById(id);
        if (car == null) {
            throw new IllegalArgumentException("Car with ID " + id + " not found");
        }
        return car;
    }

    @Override
    public void delete(int id) throws SQLException {
        getById(id);
        repository.delete(id);
    }
}
