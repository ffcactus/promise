package com.promise.platform.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.platform.auth.service.UserService;
import com.promise.platform.sdk.dto.auth.CreateUserRequestV1;
import com.promise.platform.sdk.dto.auth.GetUserResponseV1;

@RestController
@RequestMapping("/rest/v1/user")
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping
	ResponseEntity<GetUserResponseV1> create(@RequestBody CreateUserRequestV1 request) {
		var user = userService.create(request);
		return new ResponseEntity<GetUserResponseV1>(user.toResponseV1(), HttpStatus.CREATED);
	}
}
