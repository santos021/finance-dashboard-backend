package com.fdb.finance_project_backend.controller;

import com.fdb.finance_project_backend.entity.User;
import com.fdb.finance_project_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@AllArgsConstructor
public class UserController {

	private final UserService userService;


	@PostMapping
	public User createUser(@RequestBody User user){
		System.out.println(user);
		return userService.createUser(user);
	}

	@GetMapping
	public List<User> getAllUser(){
		return userService.getAllUsers();
	}
}
