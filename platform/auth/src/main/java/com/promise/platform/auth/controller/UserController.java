package com.promise.platform.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.platform.auth.service.UserService;
import com.promise.platform.sdk.dto.auth.AddUserToCompanyRequestV1;
import com.promise.platform.sdk.dto.auth.CreateUserRequestV1;
import com.promise.platform.sdk.dto.user.GetUserResponseV1;

@RestController
@RequestMapping("/rest/v1/user")
public class UserController
{
    @Autowired
    UserService userService;

    /**
     * Handle the request to create a user.
     *
     * @param request The {@link CreateUserRequestV1} represents the user to create.
     * @return The HTTP response including the {@link GetUserResponseV1} represents
     *         the user created.
     */
    @PostMapping
    ResponseEntity<GetUserResponseV1> create(@RequestBody CreateUserRequestV1 request)
    {
        final var user = userService.create(request);
        return new ResponseEntity<>(user.toResponseV1(), HttpStatus.CREATED);
    }

    /**
     * Handle the request to add a user to a company.
     * 
     * @param request The {@link AddUserToCompanyRequestV1}
     * @return The HTTP response including the {@link GetUserResponseV1} in which
     *         the <tt>companyUri</tt> should be set.
     */
    @PostMapping("/actions/add-to-company")
    ResponseEntity<GetUserResponseV1> addToCompany(@RequestBody AddUserToCompanyRequestV1 request)
    {
        // TODO
        return null;
    }
}
