package com.example.demo.Utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbOpsUtil {

    private final JdbcTemplate jdbcTemplate;

    public DbOpsUtil(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // SELECT with params
    public <T> List<T> getWithParams(String sql, Object[] params, RowMapper<T> mapper) {
        return jdbcTemplate.query(sql, params, mapper);
    }

    // INSERT/UPDATE with params
    public int postWithParams(String sql, Object[] params) {
        return jdbcTemplate.update(sql, params);
    }

    // DELETE with params
    public int deleteWithParams(String sql, Object[] params) {
        return jdbcTemplate.update(sql, params);
    }
}
