package org.example.jdbc.dao;

import org.example.jdbc.model.Model;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/13/2024
 */
@Component
@Primary
public class ModelDAOImpl implements ModelDAO {

    NamedParameterJdbcTemplate jdbcTemplate;

    public ModelDAOImpl(DataSource dataSource){
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public void saveOrUpdate(Model model) {
        String sql;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", model.getId())
                .addValue("name", model.getName())
                .addValue("email", model.getEmail());

        if (this.existsById(model.getId())) {
            sql = "UPDATE model SET name = :name, email = :email WHERE id = :id";
        } else {
            sql = "INSERT INTO model (id, name, email) VALUES (:id, :name, :email)";
        }

        jdbcTemplate.update(sql, params);
    }

    public void delete(int modelId) {
        String sql = "DELETE FROM model WHERE id = :id";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", modelId);
        jdbcTemplate.update(sql, param);
    }

    public Model get(int modelId) {
        String sql = "SELECT * FROM contact WHERE contact_id = " + modelId;

        return jdbcTemplate.query(sql, rs -> {
            if (rs.next()) {
                return Model.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .email(rs.getString("email"))
                        .build();
            } else {
            return null;
            }
        });
    }

    public List<Model> list() {
        String sql = "SELECT * FROM model";

        return jdbcTemplate.query(sql, (rs, rowNum) -> Model.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .email(rs.getString("email"))
                .build());
    }

    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM model WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        Integer count = jdbcTemplate.queryForObject(sql, params, Integer.class);
        return count != null && count > 0;
    }

}
