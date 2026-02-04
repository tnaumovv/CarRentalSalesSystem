package com.carrental.service;

import com.carrental.entity.Car;
import com.carrental.entity.CarCategory;
import com.carrental.respository.CarRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CarService extends BaseService<Car> {
    private CarRepository repository;

    public CarService() {
        this.repository = new CarRepository();
    }

    @Override
    public void add(Car car) {
        validateCar(car);
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
        validateCar(car);
        repository.update(car);
    }

    @Override
    public void delete(int id) {
        getById(id);
        repository.delete(id);
    }

    private void validateCar(Car car) {
        if (car.getBrand() == null || car.getBrand().trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be empty");
        }

        if (car.getModel() == null || car.getModel().trim().isEmpty()) {
            throw new IllegalArgumentException("Model cannot be empty");
        }

        if (car.getLocation() == null || car.getLocation().trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty");
        }

        validateYear(car.getYear());
        validatePrice(car.getPrice());
    }

    public List<Car> findByBrand(String brand) {
        return repository.getAll().stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    public List<Car> findByCategory(CarCategory category) {
        return repository.getAll().stream()
                .filter(car -> car.getCategory() == category)
                .collect(Collectors.toList());
    }

    public List<Car> findCarsAbovePrice(double minPrice) {
        return repository.getAll().stream()
                .filter(car -> car.getPrice() > minPrice)
                .collect(Collectors.toList());
    }

    public List<Car> sortByPrice() {
        return repository.getAll().stream()
                .sorted((c1, c2) -> Double.compare(c1.getPrice(), c2.getPrice()))
                .collect(Collectors.toList());
    }

    public double getAveragePrice() {
        return repository.getAll().stream()
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0.0);
    }

    public long countCarsByYear(int year) {
        return repository.getAll().stream()
                .filter(car -> car.getYear() == year)
                .count();
    }
}