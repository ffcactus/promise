package com.promise.platform.sdk.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "password" })
public class CreateUserRequestV1 {
	public String username;
	public String password;
	public String email;
	public String partition;
	public String scope;
	public String authorities;

//	public User toModel() {
//		return new User(UUID.randomUUID().toString(), username, password, email, partition, scope, authorities);
//	}
}
