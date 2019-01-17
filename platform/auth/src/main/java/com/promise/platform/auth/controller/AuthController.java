package com.promise.platform.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.promise.platform.auth.dto.LoginRequest;

/**
 * The controller for auth module.
 */
@Controller
@RequestMapping("/rest/v1/auth")
public class AuthController {
	/**
	 * Login controller handle login process.
	 *
	 * @param request The login request.
	 * @return The authentication.
	 */
	@PostMapping("/login")
	public ResponseEntity<Void> login(@RequestBody LoginRequest request) {
		return null;
	}
}
