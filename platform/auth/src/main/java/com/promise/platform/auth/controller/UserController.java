package com.promise.platform.auth.controller;

import com.promise.platform.auth.sdk.dto.*;
import com.promise.platform.auth.service.UserService;
import com.promise.platform.common.controller.CommonExceptionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController extends CommonExceptionController {
    @Autowired
    UserService userService;

    /**
     * hander user registration process.
     *
     * @param request The {@link RegisterUserRequestV1} represents the registration information.
     * @return The HTTP response including the {@link GetUserResponseV1} represents
     * the user created.
     */
    @PostMapping
    public ResponseEntity<GetUserResponseV1> register(@RequestBody RegisterUserRequestV1 request) {
        userService.register(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public GetUserResponseV1 getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @GetMapping
    public List<GetUserResponseV1> getAll() {
        return userService.fetchAllUsers();
    }

    @PutMapping("/{id}")
    public GetUserResponseV1 update(@PathVariable("id") Long id, @RequestBody UpdateUserRequestV1 request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @PostMapping("/{id}/actions/change-password")
    public void changePassword(@PathVariable("id") Long userId, @RequestBody ChangePasswordRequestV1 request) {
        userService.changePassword(userId, request.getOldPassword(), request.getNewPassword());
    }

    @PostMapping("/{id}/actions/reset-password")
    public void resetPassword(@PathVariable("id") Long userId, String newPassword) {
        userService.resetPassword(userId, newPassword);
    }

    /**
     * Handle the request to add a user to a company.
     *
     * @param request The {@link AddUserToCompanyRequestV1}
     * @return The HTTP response including the {@link GetUserResponseV1} in which
     * the <tt>companyUri</tt> should be set.
     */
    @PostMapping("/actions/add-to-company")
    ResponseEntity<GetUserResponseV1> addToCompany(@RequestBody AddUserToCompanyRequestV1 request) {
        // TODO
        return null;
    }

//    @ResponseStatus(HttpStatus.CONFLICT)
//    @ExceptionHandler({UsernameExistException.class, EmailExistException.class, UserNotExistException.class})
//    @ResponseBody
//    ErrorMessageResponseV1 handleRegisterException(Exception e) {
//        var converter = (ErrorResponseConverter) e;
//        return converter.convertToErrorResponse();
//    }
}
