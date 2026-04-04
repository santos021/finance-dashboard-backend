package com.fdb.finance_project_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/api/admin/users").permitAll()
						.requestMatchers(
								"/v3/api-docs/**",
								"/swagger-ui/**",
								"/swagger-ui.html"
						).permitAll()
						.requestMatchers("/api/admin/**").hasRole("ADMIN")
						.requestMatchers("/api/analyst/**").hasAnyRole("ADMIN","ANALYST")
						.requestMatchers("/api/records/**").hasAnyRole("ADMIN","ANALYST")
						.anyRequest().authenticated()
				)
				.httpBasic(Customizer.withDefaults());
		return http.build();
	}
}
