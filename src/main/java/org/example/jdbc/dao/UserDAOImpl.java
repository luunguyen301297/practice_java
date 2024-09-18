package org.example.jdbc.dao;

import org.example.jdbc.SimplePage;
import org.example.jdbc.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

/**
 * @Author: luunguyen301297
 * @LastModified: 9/13/2024
 */
@Component
@Primary
public class UserDAOImpl implements UserDAO {

    NamedParameterJdbcTemplate jdbcTemplate;

    public UserDAOImpl(DataSource dataSource){
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public void saveOrUpdate(User user) {
        String sql;
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("username", user.getUsername())
                .addValue("email", user.getEmail());

        if (this.existsById(user.getId())) {
            sql = "UPDATE \"user\" SET username = :name, email = :email WHERE id = :id";
        } else {
            sql = "INSERT INTO \"user\" (id, username, email) VALUES (:id, :name, :email)";
        }

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void delete(int userId) {
        String sql = "DELETE FROM \"user\" WHERE id = :id";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", userId);
        jdbcTemplate.update(sql, param);
    }

    @Override
    public Optional<User> get(int userId) {
        String sql = "SELECT * FROM \"user\" WHERE id = :id";
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", userId);

        return Optional.ofNullable(jdbcTemplate.query(sql, param, rs -> {
            if (rs.next()) {
                return User.builder()
                        .id(rs.getInt("id"))
                        .username(rs.getString("username"))
                        .email(rs.getString("email"))
                        .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                        .build();
            } else {
                return null;
            }
        }));
    }

    @Override
    public SimplePage<User> list(int pageSize, int pageNumber) {
        int offset = pageSize * pageNumber;

        String sql = "SELECT * FROM \"user\" LIMIT :limit OFFSET :offset";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("limit", pageSize)
                .addValue("offset", offset);

        List<User> users = jdbcTemplate.query(sql, params, (rs, rowNum) -> User.builder()
                .id(rs.getInt("id"))
                .username(rs.getString("username"))
                .email(rs.getString("email"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .build());

        String countSql = "SELECT COUNT(id) FROM \"user\"";

        Integer total = jdbcTemplate.queryForObject(countSql, new MapSqlParameterSource(), Integer.class);

        total = total != null ? total : 0;

        return new SimplePage<>(users, total, pageNumber, pageSize);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT 1 FROM \"user\" WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        return jdbcTemplate.queryForObject(sql, params, (rs, rowNum) -> true) != null;
    }

}
