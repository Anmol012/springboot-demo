package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@GetMapping
	public List<User> getAll() {
		return service.getUsers();
	}

	@GetMapping("/{id}")
	public User getOne(@PathVariable int id) {
		return service.getUserById(id);
	}

	@PostMapping
	public String create(@RequestBody User user) {
		service.createUser(user);
		return "User created";
	}

	@PutMapping("/{id}")
	public String update(@PathVariable int id, @RequestBody User user) {
		user.setId(id);
		service.updateUser(user);
		return "User updated";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable int id) {
		service.deleteUser(id);
		return "User deleted";
	}
}
