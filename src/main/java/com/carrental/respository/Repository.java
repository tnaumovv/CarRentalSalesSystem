package com.carrental.respository;
import java.sql.SQLException;
import java.util.List;

public interface Repository<T> {
    void add(T entity) throws SQLException;
    List<T> getAll() throws SQLException;
    T getById(int id) throws SQLException;
    void delete(int id) throws SQLException;
}
