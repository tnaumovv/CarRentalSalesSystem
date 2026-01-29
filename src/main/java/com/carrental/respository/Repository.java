package com.carrental.respository;

import java.util.List;

public interface Repository<T> {
    void add(T entity);
    List<T> getAll();
    T getById(int id);
    void delete(int id);
}
