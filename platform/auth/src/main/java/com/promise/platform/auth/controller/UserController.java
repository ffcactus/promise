package com.promise.platform.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.platform.auth.dto.CreateUserRequest;
import com.promise.platform.auth.dto.GetUserResponse;
import com.promise.platform.auth.service.UserService;

@RestController
@RequestMapping("/rest/v1/user")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping
	ResponseEntity<GetUserResponse> create(@RequestBody CreateUserRequest request) {
		GetUserResponse resposne = userService.create(request);
		return new ResponseEntity<GetUserResponse>(resposne, HttpStatus.CREATED);
	}
}
