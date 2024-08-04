package org.example.design_pattern.dao;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
public interface Dao<T> {

    List<T> findAll();

    Optional<T> findById(int id);

    void save(T t);

    void update(T t);

    void delete(T t);

}
