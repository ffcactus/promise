package com.promise.platform.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promise.platform.auth.dto.LoginRequest;
import com.promise.platform.auth.model.User;
import com.promise.platform.auth.repository.UserRepository;
import com.promise.platform.auth.sdk.model.JwtUser;

@Service
public class LoginService {
	@Autowired
	UserRepository userRepository;

	/**
	 * Handle login operation.
	 * 
	 * @param request The login request.
	 * @return The login response in which token is included.
	 */
	public JwtUser Login(LoginRequest request) {
		Optional<User> user = userRepository.findByUsername(request.getUsername());
		if (user.isEmpty()) {
			throw new RuntimeException("Invalid username or password.");
		}
		User savedUser = user.get();
		if (!savedUser.getPassword().equals(request.getPassword())) {
			throw new RuntimeException("Invalid username or password.");
		}

		return new JwtUser(savedUser.getUsername(), savedUser.getPartition(), savedUser.getScope(),
				savedUser.getRole());
	}

	public void logout(String token) {
		// TODO Auto-generated method stub

	}

	public Optional<User> info(String token) {
		Optional<User> user = userRepository.findByToken(token);
		if (user.isEmpty()) {
			throw new RuntimeException("Unknown token.");
		}
		return user;
	}
}
