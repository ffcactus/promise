package com.promise.platform.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promise.platform.auth.dto.CreateUserRequest;
import com.promise.platform.auth.dto.GetUserResponse;
import com.promise.platform.auth.model.User;
import com.promise.platform.auth.repository.UserRepository;

/**
 * The service for user operation.
 *
 */
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;

	public GetUserResponse create(CreateUserRequest request) {
		User user = userRepository.save(request.toModel());
		return new GetUserResponse(user);
	}
}
