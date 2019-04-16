package com.promise.platform.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promise.platform.auth.model.User;
import com.promise.platform.auth.repository.UserRepository;
import com.promise.platform.sdk.dto.auth.LoginRequestV1;
import com.promise.platform.sdk.exception.LoginFailureException;
import com.promise.platform.sdk.model.JwtUser;
import com.promise.platform.sdk.util.JwtTokenGenerator;

import io.micrometer.core.instrument.util.StringUtils;

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
	public JwtUser Login(LoginRequestV1 request) {
		Optional<User> user = userRepository.findByUsername(request.getUsername());
		if (user.isEmpty()) {
			throw new LoginFailureException();
		}
		User savedUser = user.get();
		if (!savedUser.getPassword().equals(request.getPassword())) {
			throw new LoginFailureException();
		}
		// no repeated login allowed.
		if (StringUtils.isEmpty(savedUser.getToken())) {
			throw new LoginFailureException();
		}
		// set token and update it in DB.
		savedUser.setToken("Bearer " + JwtTokenGenerator.generateToken(savedUser));		
		userRepository.save(savedUser);
		
		return savedUser;
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
