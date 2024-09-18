package org.example.jdbc.dao;

import org.example.jdbc.SimplePage;
import org.example.jdbc.model.User;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/13/2024
 */
@Component
public interface UserDAO {

    void saveOrUpdate(User user);

    void delete(int modelId);

    Optional<User> get(int modelId);

    SimplePage<User> list(int pageSize, int pageNumber);

    boolean existsById(int modelId);

}
