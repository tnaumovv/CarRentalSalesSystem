package com.carrental;

import com.carrental.controller.AuthController;
import com.carrental.controller.CarController;
import com.carrental.entity.User;
import com.carrental.config.DatabaseConnection;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("╔═══════════════════════════════════════╗");
            System.out.println("║      CAR RENTAL AND SALES SYSTEM      ║");
            System.out.println("╚═══════════════════════════════════════╝");

            AuthController authController = new AuthController();
            User currentUser = null;

            boolean authenticated = false;
            while (!authenticated) {
                displayAuthMenu();
                System.out.print("Choose an option: ");

                try {
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            currentUser = authController.login(scanner);
                            if (currentUser != null) {
                                authenticated = true;
                            }
                            break;
                        case 2:
                            authController.register(scanner);
                            break;
                        case 3:
                            System.out.println("\nGoodbye!");
                            return;
                        default:
                            System.out.println("\nInvalid choice. Try again.");
                    }
                } catch (Exception e) {
                    System.out.println("\nInput error: " + e.getMessage());
                    scanner.nextLine();
                }

                if (!authenticated) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
            }

            CarController carController = new CarController();
            boolean running = true;

            while (running) {
                displayMainMenu();
                System.out.print("Choose an option: ");

                try {
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            carController.addCar(scanner);
                            break;
                        case 2:
                            carController.viewAllCars();
                            break;
                        case 3:
                            carController.deleteCar(scanner);
                            break;
                        case 4:
                            carController.updateCar(scanner);
                            break;
                        case 5:
                            carController.searchByBrand(scanner);
                            break;
                        case 6:
                            carController.searchByCategory(scanner);
                            break;
                        case 7:
                            carController.viewCarsSortedByPrice();
                            break;
                        case 8:
                            System.out.println("\nLogging out...");
                            System.out.println("Thank you for using our system!");
                            running = false;
                            break;
                        default:
                            System.out.println("\nInvalid choice. Try again.");
                    }
                } catch (Exception e) {
                    System.out.println("\nInput error: " + e.getMessage());
                    scanner.nextLine();
                }

                if (running) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
            }

        } catch (Exception e) {
            System.out.println("Database connection error: " + e.getMessage());
        }

        scanner.close();
    }

    private static void displayAuthMenu() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│         AUTHENTICATION MENU         │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│  1. Login                           │");
        System.out.println("│  2. Register                        │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│  3. Exit                            │");
        System.out.println("└─────────────────────────────────────┘");
    }

    private static void displayMainMenu() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│              MAIN MENU              │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│  1. Add New Car                     │");
        System.out.println("│  2. View All Cars                   │");
        System.out.println("│  3. Delete Car                      │");
        System.out.println("│  4. Update Car                      │");
        System.out.println("│  5. Search by Brand                 │");
        System.out.println("│  6. Search by Category              │");
        System.out.println("│  7. View Cars Sorted by Price       │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│  8. Logout                          │");
        System.out.println("└─────────────────────────────────────┘");
    }
}