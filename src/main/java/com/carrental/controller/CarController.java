package com.carrental.controller;

import com.carrental.entity.Car;
import com.carrental.service.CarService;
import com.carrental.entity.Vehicle;
import com.carrental.service.BaseService;

import java.util.List;
import java.util.Scanner;

public class CarController {
    private CarService service;

    public CarController() {
        this.service = new CarService();
    }

    public void addCar(Scanner scanner) {
        try {
            System.out.println("\n=== ADD NEW CAR ===");

            System.out.print("Brand: ");
            String brand = scanner.nextLine();

            System.out.print("Model: ");
            String model = scanner.nextLine();

            System.out.print("Year: ");
            int year = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Description: ");
            String description = scanner.nextLine();

            System.out.print("Location: ");
            String location = scanner.nextLine();

            System.out.print("Price: ");
            double price = scanner.nextDouble();

            Car car = new Car(brand, model, year, description, location, price);
            service.add(car);

            System.out.println("\n✓ Car added successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("\n Validation error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n Error adding car: " + e.getMessage());
        }
    }

    public void viewAllCars() {
        try {
            System.out.println("\n=== ALL CARS ===");
            List<Car> cars = service.getAll();

            if (cars.isEmpty()) {
                System.out.println("No cars in database.");
            } else {
                System.out.println("\nTotal cars: " + cars.size());
                System.out.println("-".repeat(80));
                for (Car car : cars) {
                    System.out.println(car);
                }
                System.out.println("-".repeat(80));
            }

        } catch (Exception e) {
            System.out.println("\n Error: " + e.getMessage());
        }
    }

    public void updateCar(Scanner scanner) {
        try {
            System.out.println("\n=== UPDATE CAR ===");

            System.out.print("Enter car ID to update: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Car car = service.getById(id);
            System.out.println("\nCurrent info: " + car);

            System.out.print("\nNew brand (press Enter to keep '" + car.getBrand() + "'): ");
            String brand = scanner.nextLine();
            if (!brand.isEmpty()) car.setBrand(brand);

            System.out.print("New model (press Enter to keep '" + car.getModel() + "'): ");
            String model = scanner.nextLine();
            if (!model.isEmpty()) car.setModel(model);

            System.out.print("New year (press Enter to keep '" + car.getYear() + "'): ");
            String yearStr = scanner.nextLine();
            if (!yearStr.isEmpty()) {
                car.setYear(Integer.parseInt(yearStr));
            }

            System.out.print("New description (press Enter to keep): ");
            String description = scanner.nextLine();
            if (!description.isEmpty()) car.setDescription(description);

            System.out.print("New location (press Enter to keep '" + car.getLocation() + "'): ");
            String location = scanner.nextLine();
            if (!location.isEmpty()) car.setLocation(location);

            System.out.print("New price (press Enter to keep $" + car.getPrice() + "): ");
            String priceStr = scanner.nextLine();
            if (!priceStr.isEmpty()) {
                car.setPrice(Double.parseDouble(priceStr));
            }

            service.update(car);
            System.out.println("\n✓ Car updated successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("\n " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n Error updating car: " + e.getMessage());
        }
    }

    public void deleteCar(Scanner scanner) {
        try {
            System.out.println("\n=== DELETE CAR ===");

            System.out.print("Enter car ID to delete: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            Car car = service.getById(id);
            System.out.println("\nCar to delete: " + car);

            System.out.print("\nAre you sure? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                service.delete(id);
                System.out.println("\n✓ Car deleted successfully!");
            } else {
                System.out.println("\nOperation cancelled.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
}