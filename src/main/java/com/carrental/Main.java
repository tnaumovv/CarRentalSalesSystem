package com.carrental;

import com.carrental.controller.CarController;
import com.carrental.config.DatabaseConnection;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("╔═══════════════════════════════════════╗");
            System.out.println("║   CAR RENTAL AND SALES SYSTEM         ║");
            System.out.println("╚═══════════════════════════════════════╝");

            CarController controller = new CarController();
            boolean running = true;

            while (running) {
                displayMenu();
                System.out.print("Choose an option: ");

                try {
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1:
                            controller.addCar(scanner);
                            break;
                        case 2:
                            controller.viewAllCars();
                            break;
                        case 3:
                            controller.deleteCar(scanner);
                            break;
                        case 4:
                            System.out.println("\nThank you for using our system!");
                            running = false;
                            break;
                        default:
                            System.out.println("\n❌ Invalid choice. Try again.");
                    }
                } catch (Exception e) {
                    System.out.println("\n❌ Input error: " + e.getMessage());
                    scanner.nextLine();
                }

                if (running) {
                    System.out.println("\nPress Enter to continue...");
                    scanner.nextLine();
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Database connection error: " + e.getMessage());
        }

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│              MAIN MENU              │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│  1. Add New Car                     │");
        System.out.println("│  2. View All Cars                   │");
        System.out.println("│  3. Delete Car                      │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│  4. Exit                            │");
        System.out.println("└─────────────────────────────────────┘");
    }
}