package com.example.SpringJdbcDemo.repository;

import com.example.SpringJdbcDemo.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = (rs, rowNum) -> {

        User user = new User();

        user.setId(rs.getLong("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));

        return user;
    };

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // CREATE
    public int createUser(User user) {

        String sql = """
                INSERT INTO users(name, email, password)
                VALUES (?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    // READ ALL
    public List<User> getUsers() {

        String sql = """
                SELECT id, name, email, password
                FROM users
                """;

        return jdbcTemplate.query(sql, userRowMapper);
    }

    // READ ONE
    public User getUserById(Long id) {

        String sql = """
                SELECT id, name, email, password
                FROM users
                WHERE id = ?
                """;

        return jdbcTemplate.queryForObject(
                sql,
                userRowMapper,
                id
        );
    }

    // UPDATE
    public int updateUser(User user, Long id) {

        String sql = """
                UPDATE users
                SET name = ?, email = ?, password = ?
                WHERE id = ?
                """;

        return jdbcTemplate.update(
                sql,
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                id
        );
    }

    // DELETE
    public int deleteUser(Long id) {

        String sql = """
                DELETE FROM users
                WHERE id = ?
                """;

        return jdbcTemplate.update(sql, id);
    }
}