package com.promise.platform.auth.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promise.platform.auth.model.SessionInfo;
import com.promise.platform.auth.model.User;
import com.promise.platform.auth.repository.SessionRepository;
import com.promise.platform.auth.repository.UserRepository;
import com.promise.platform.sdk.dto.auth.LoginRequestV1;
import com.promise.platform.sdk.exception.UnauthorizedException;
import com.promise.platform.sdk.util.JwtTokenGenerator;

@Service
public class SessionService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	SessionRepository sessionRepository;
	
	@Autowired
	JwtTokenGenerator jwtTokenGenerator;

	/**
	 * Handle login operation.
	 * 
	 * @param request The login request.
	 * @return The token string.
	 */
	public String Login(LoginRequestV1 request) {
		Optional<User> user = userRepository.findByUsername(request.getUsername());
		if (user.isEmpty()) {
			throw new UnauthorizedException();
		}
		User savedUser = user.get();
		if (!savedUser.getPassword().equals(request.getPassword())) {
			throw new UnauthorizedException();
		}
		// set token and update it in DB.
		String token = jwtTokenGenerator.generateToken(savedUser);
		SessionInfo sessionInfo = new SessionInfo(token, savedUser.getId());
		sessionRepository.save(sessionInfo);
		return token;
	}

	/**
	 * Logout process, remove the session.
	 * @param token
	 */
	public void Logout(String token) {
		if (sessionRepository.existsById(token)) {
			sessionRepository.deleteById(token);
		} else {
			throw new UnauthorizedException();
		}
	}

	public Optional<User> info(String token) {
		Optional<User> user = userRepository.findByToken(token);
		if (user.isEmpty()) {
			throw new UnauthorizedException();
		}
		return user;
	}
}
