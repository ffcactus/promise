package com.promise.platform.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.promise.platform.auth.model.User;

/**
 * User repository interface.
 */
public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByUsername(String username);

	Optional<User> findByToken(String token);
}
