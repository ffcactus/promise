package com.promise.platform.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.promise.platform.auth.exception.EmailExistException;
import com.promise.platform.auth.exception.UsernameExistException;
import com.promise.platform.auth.model.User;
import com.promise.platform.auth.repository.UserRepository;
import com.promise.platform.sdk.dto.auth.RegisterUserRequestV1;

/**
 * The service for user operation.
 *
 */
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	@Transactional(isolation=Isolation.SERIALIZABLE)
	public void register(RegisterUserRequestV1 request) {
		var existedUsername = userRepository.findByUsername(request.username);
		if (existedUsername.isPresent()) {
			throw new UsernameExistException();
		}
		var existedEmail = userRepository.findByEmail(request.email);
		if (existedEmail.isPresent()) {
			throw new EmailExistException();
		}
		userRepository.save(new User(request));
	}
}
