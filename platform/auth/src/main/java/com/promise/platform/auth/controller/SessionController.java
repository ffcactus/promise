package com.promise.platform.auth.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.platform.auth.model.User;
import com.promise.platform.auth.service.LoginService;
import com.promise.platform.sdk.controller.ExceptionController;
import com.promise.platform.sdk.dto.auth.LoginRequestV1;
import com.promise.platform.sdk.dto.user.GetUserResponseV1;
import com.promise.platform.sdk.model.JwtUser;

/**
 * The controller for login and logout.
 *
 */
@RestController
@RequestMapping("/api/v1/session")
public class SessionController extends ExceptionController {
	@Autowired
	LoginService service;

	/**
	 * Handle login process.
	 * 
	 * @param request The login request
	 * @return The HTTP response that includes the access token in the head and
	 *         access token information in the response body.
	 */
	@PostMapping("/login")
	public ResponseEntity<Void> login(@RequestBody final LoginRequestV1 request) {
		JwtUser jwtUser = service.Login(request);
		final HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		responseHeaders.set("Authorization", jwtUser.getToken());
		return ResponseEntity.noContent().headers(responseHeaders).build();
	}

	@PostMapping("/logout")
	public void logout(@RequestHeader("Authorization") String token) {
		service.logout(token);
	}

	@GetMapping("/info")
	public ResponseEntity<GetUserResponseV1> info(@RequestHeader("Authorization") String token) {
		Optional<User> user = service.info(token);
		if (user.isEmpty()) {
			return new ResponseEntity<GetUserResponseV1>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<GetUserResponseV1>(user.get().toResponseV1(), HttpStatus.OK);
	}
}
