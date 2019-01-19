package com.promise.platform.auth.dto;

import java.util.UUID;

import com.promise.platform.auth.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "password" })
public class CreateUserRequest {
	private String username;
	private String password;
	private String email;
	private String partition;
	private String scope;
	private String authorities;

	public User toModel() {
		return new User(UUID.randomUUID().toString(), username, password, email, partition, scope, authorities);
	}
}
