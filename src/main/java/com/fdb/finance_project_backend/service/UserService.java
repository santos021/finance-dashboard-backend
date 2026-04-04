package com.fdb.finance_project_backend.service;

import com.fdb.finance_project_backend.entity.User;
import com.fdb.finance_project_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

//	public UserService(UserRepository userRepository){
//		this.userRepository = userRepository;
//	}

	public User createUser(User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
}
