package com.carrental.controller;

import com.carrental.entity.User;
import com.carrental.service.UserService;

import java.util.Scanner;

public class AuthController {
    private UserService service;

    public AuthController() {
        this.service = new UserService();
    }

    public void register(Scanner scanner) {
        try {
            System.out.println("\n=== REGISTRATION ===");

            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            service.register(username, password);

            System.out.println("\n✓ Registration successful!");
            System.out.println("You can now login with your credentials.");

        } catch (IllegalArgumentException e) {
            System.out.println("\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    public User login(Scanner scanner) {
        try {
            System.out.println("\n=== LOGIN ===");

            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Password: ");
            String password = scanner.nextLine();

            User user = service.login(username, password);

            System.out.println("\n✓ Login successful!");
            System.out.println("Welcome, " + user.getUsername() + "!");

            return user;

        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n❌ Error: " + e.getMessage());
        }

        return null;
    }
}