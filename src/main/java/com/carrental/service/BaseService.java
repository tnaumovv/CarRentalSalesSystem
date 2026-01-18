package com.carrental.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public abstract class BaseService<T> {
    public abstract void add(T entity) throws SQLException;
    public abstract List<T> getAll() throws SQLException;
    public abstract T getById(int id) throws SQLException;
    public abstract void delete(int id) throws SQLException;

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
