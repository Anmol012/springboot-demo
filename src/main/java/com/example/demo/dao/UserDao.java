package com.example.demo.dao;


import com.example.demo.model.User;
import com.example.demo.Utils.DbOpsUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    private final DbOpsUtil db;

    public UserDao(DbOpsUtil db) {
        this.db = db;
    }

    private final RowMapper<User> userMapper = (rs, rowNum) -> {
        User u = new User();
        u.setId(rs.getInt("id"));
        u.setName(rs.getString("name"));
        u.setEmail(rs.getString("email"));
        return u;
    };

    public List<User> findAll() {
        return db.getWithParams("SELECT * FROM users", new Object[]{}, userMapper);
    }

    public User findById(int id) {
        List<User> list = db.getWithParams(
                "SELECT * FROM users WHERE id = ?",
                new Object[]{id},
                userMapper
        );
        return list.isEmpty() ? null : list.get(0);
    }

    public int save(User user) {
        return db.postWithParams(
                "INSERT INTO users(name, email) VALUES (?, ?)",
                new Object[]{user.getName(), user.getEmail()}
        );
    }

    public int update(User user) {
        return db.postWithParams(
                "UPDATE users SET name = ?, email = ? WHERE id = ?",
                new Object[]{user.getName(), user.getEmail(), user.getId()}
        );
    }

    public int delete(int id) {
        return db.deleteWithParams(
                "DELETE FROM users WHERE id = ?",
                new Object[]{id}
        );
    }
}
