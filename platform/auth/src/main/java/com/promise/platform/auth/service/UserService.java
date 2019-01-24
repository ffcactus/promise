package com.promise.platform.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promise.platform.auth.model.User;
import com.promise.platform.auth.repository.UserRepository;
import com.promise.platform.sdk.dto.auth.CreateUserRequestV1;

/**
 * The service for user operation.
 *
 */
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public User create(CreateUserRequestV1 request) {
		return userRepository.save(new User(request));
	}
}
