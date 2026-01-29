package com.carrental.service;

import com.carrental.entity.Car;
import com.carrental.repository.CarRepository;
import com.carrental.respository.Repository;

import java.util.List;

public class CarService extends BaseService<Car> {
    private CarRepository repository;

    public CarService() {
        this.repository = new CarRepository();
    }

    @Override
    public void add(Car car) {
        validateYear(car.getYear());
        validatePrice(car.getPrice());
        repository.add(car);
    }


    @Override
    public List<Car> getAll() {
        return repository.getAll();
    }

    @Override
    public Car getById(int id) {
        Car car = repository.getById(id);
        if (car == null) {
            throw new IllegalArgumentException("Car with ID " + id + " not found");
        }
        return car;
    }

    public void update(Car car) {
        getById(car.getId());
        validateYear(car.getYear());
        validatePrice(car.getPrice());
        repository.update(car);
    }

    @Override
    public void delete(int id) {
        getById(id);
        repository.delete(id);
    }
}