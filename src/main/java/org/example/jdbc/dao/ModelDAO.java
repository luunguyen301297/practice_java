package org.example.jdbc.dao;

import org.example.jdbc.model.Model;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/13/2024
 */
@Component
public interface ModelDAO {

    void saveOrUpdate(Model model);

    void delete(int modelId);

    Model get(int modelId);

    List<Model> list();

    boolean existsById(int modelId);

}
