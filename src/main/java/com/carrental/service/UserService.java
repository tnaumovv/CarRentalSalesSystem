package com.carrental.service;

import com.carrental.entity.User;
import com.carrental.respository.UserRepository;

public class UserService {
    private UserRepository repository;

    public UserService() {
        this.repository = new UserRepository();
    }

    public void register(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters");
        }

        if (repository.userExists(username)) {
            throw new IllegalArgumentException("Username already exists");
        }

        User user = new User(username, password);
        repository.register(user);
    }

    public User login(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        User user = repository.login(username, password);

        if (user == null) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return user;
    }
}