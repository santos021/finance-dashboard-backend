package com.fdb.finance_project_backend.service;

import com.fdb.finance_project_backend.entity.Status;
import com.fdb.finance_project_backend.entity.User;
import com.fdb.finance_project_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepository;



	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email)
				.orElseThrow(()-> new UsernameNotFoundException("User not found"));

		if (user.getStatus() == Status.INACTIVE) {
			throw new UsernameNotFoundException("User is inactive");
		}

		return org.springframework.security.core.userdetails.User
				.withUsername(user.getEmail())
				.password(user.getPassword())
				.roles(user.getRole().name())
				.build();
	}
}
