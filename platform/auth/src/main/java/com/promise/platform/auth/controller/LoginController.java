package com.promise.platform.auth.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.promise.platform.auth.dto.GetUserResponse;
import com.promise.platform.auth.dto.LoginRequest;
import com.promise.platform.auth.model.User;
import com.promise.platform.auth.service.LoginService;
import com.promise.platform.sdk.model.JwtUser;
import com.promise.platform.sdk.util.JwtTokenGenerator;

@RestController
public class LoginController {
	@Value("${jwt.secret}")
	private String secret;

	@Autowired
	LoginService service;

	@PostMapping("/login")
	public ResponseEntity<Void> login(@RequestBody final LoginRequest request) {
		JwtUser jwtUser = service.Login(request);
		final HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Authorization", "Bearer " + JwtTokenGenerator.generateToken(jwtUser, secret));
		return ResponseEntity.noContent().headers(responseHeaders).build();
	}

	@PostMapping("/logout")
	public void logout(@RequestHeader("promise-token") String token) {
		service.logout(token);
	}

	@GetMapping("/info")
	public ResponseEntity<GetUserResponse> info(@RequestHeader("promise-token") String token) {
		Optional<User> user = service.info(token);
		if (user.isEmpty()) {
			return new ResponseEntity<GetUserResponse>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<GetUserResponse>(new GetUserResponse(user.get()), HttpStatus.OK);
	}
}
