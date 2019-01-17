package com.promise.platform.auth.model;

import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The module of the user.
 */
@Data
@NoArgsConstructor
public class User {
	public String username;
	public String password;
	public String email;
	public Set<String> role;
}
