package com.promise.platform.sdk.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The login request body.
 *
 */
@Data
@NoArgsConstructor
public class LoginRequestV1 {
	public String username;
	public String password;
}
