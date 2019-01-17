package com.promise.platform.auth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the login request.
 */
@Data
@NoArgsConstructor
public class LoginRequest {
	public String username;
	public String password;
}
