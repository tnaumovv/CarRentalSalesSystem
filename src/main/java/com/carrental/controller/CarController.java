package com.carrental.controller;

import com.carrental.entity.Car;
import com.carrental.entity.CarCategory;
import com.carrental.service.CarService;

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

            System.out.println("\nSelect category:");
            System.out.println("1. Sedan");
            System.out.println("2. SUV");
            System.out.println("3. Truck");
            System.out.println("4. Sports Car");
            System.out.println("5. Van");
            System.out.println("6. Compact");
            System.out.print("Choice: ");
            int categoryChoice = scanner.nextInt();
            scanner.nextLine();

            CarCategory category = null;
            switch(categoryChoice) {
                case 1: category = CarCategory.SEDAN; break;
                case 2: category = CarCategory.SUV; break;
                case 3: category = CarCategory.TRUCK; break;
                case 4: category = CarCategory.SPORTS; break;
                case 5: category = CarCategory.VAN; break;
                case 6: category = CarCategory.COMPACT; break;
            }

            System.out.print("Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            Car car = new Car(brand, model, year, description, location, price, category);
            service.add(car);

            System.out.println("\n✓ Car added successfully!");

        } catch (IllegalArgumentException e) {
            System.out.println("\nValidation error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nError adding car: " + e.getMessage());
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
            System.out.println("\nError: " + e.getMessage());
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
            System.out.println("\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nError updating car: " + e.getMessage());
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
            System.out.println("\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    public void searchByBrand(Scanner scanner) {
        try {
            System.out.println("\n=== SEARCH BY BRAND ===");

            System.out.print("Enter brand: ");
            String brand = scanner.nextLine();

            List<Car> cars = service.findByBrand(brand);

            if (cars.isEmpty()) {
                System.out.println("No cars found with brand: " + brand);
            } else {
                System.out.println("\nFound " + cars.size() + " car(s):");
                System.out.println("-".repeat(80));
                for (Car car : cars) {
                    System.out.println(car);
                }
                System.out.println("-".repeat(80));
            }

        } catch (Exception e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

    public void searchByCategory(Scanner scanner) {
        try {
            System.out.println("\n=== SEARCH BY CATEGORY ===");

            System.out.println("Select category:");
            System.out.println("1. Sedan");
            System.out.println("2. SUV");
            System.out.println("3. Truck");
            System.out.println("4. Sports Car");
            System.out.println("5. Van");
            System.out.println("6. Compact");
            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            CarCategory category = null;
            switch(choice) {
                case 1: category = CarCategory.SEDAN; break;
                case 2: category = CarCategory.SUV; break;
                case 3: category = CarCategory.TRUCK; break;
                case 4: category = CarCategory.SPORTS; break;
                case 5: category = CarCategory.VAN; break;
                case 6: category = CarCategory.COMPACT; break;
            }

            if (category != null) {
                List<Car> cars = service.findByCategory(category);

                if (cars.isEmpty()) {
                    System.out.println("No cars found in category: " + category.getDisplayName());
                } else {
                    System.out.println("\nFound " + cars.size() + " car(s):");
                    System.out.println("-".repeat(80));
                    for (Car car : cars) {
                        System.out.println(car);
                    }
                    System.out.println("-".repeat(80));
                }
            }

        } catch (Exception e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }

    public void viewCarsSortedByPrice() {
        try {
            System.out.println("\n=== CARS SORTED BY PRICE ===");

            List<Car> cars = service.sortByPrice();

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
            System.out.println("\n❌ Error: " + e.getMessage());
        }
    }
}