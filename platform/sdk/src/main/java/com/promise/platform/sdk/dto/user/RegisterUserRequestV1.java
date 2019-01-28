package com.promise.platform.sdk.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the user registration request body.
 *
 */
@Data
@NoArgsConstructor
public class RegisterUserRequestV1 {
	public String username;
	public String password;
	public String nickname;
	public String email;	
}
