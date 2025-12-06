package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private final UserDao dao;

	public UserService(UserDao dao) {
		this.dao = dao;
	}

	public List<User> getUsers() {
		return dao.findAll();
	}

	public User getUserById(int id) {
		return dao.findById(id);
	}

	public int createUser(User user) {
		return dao.save(user);
	}

	public int updateUser(User user) {
		return dao.update(user);
	}

	public int deleteUser(int id) {
		return dao.delete(id);
	}
}
