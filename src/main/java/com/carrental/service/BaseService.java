package com.carrental.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public abstract class BaseService<T> {
    public abstract void add(T entity);
    public abstract List<T> getAll();
    public abstract T getById(int id);
    public abstract void delete(int id);

    protected void validateYear(int year) {
        int currentYear = LocalDate.now().getYear();
        if (year < 1900 || year > currentYear + 1) {
            throw new IllegalArgumentException("Year must be between 1900 and " + (currentYear + 1));
        }
    }

    protected void validatePrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive" );
        }
    }
}
