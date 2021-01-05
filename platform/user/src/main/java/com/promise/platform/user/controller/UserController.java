package com.promise.platform.user.controller;

import com.promise.platform.common.controller.CommonExceptionController;
import com.promise.platform.sdk.dto.auth.GetUserResponseV1;
import com.promise.platform.sdk.dto.auth.PatchUserRequestV1;
import com.promise.platform.sdk.dto.user.RegisterUserRequestV1;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The user controller handles requests for user management.
 *
 * <ul>
 * <li>User can register/unregister to the Promise system.
 * <li>User can update he's personal information.
 * <li>User can reset he's password and things like that.
 * <li>Company Administrator can add/remove users to/from his company.
 * </ul>
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController extends CommonExceptionController {

    /**
     * Handle the user registration.
     *
     * @param request The {@link RegisterUserRequestV1}
     * @return The HTTP response indicate if the registration is successful.
     */
    @PostMapping
    public ResponseEntity<Void> register(@RequestBody RegisterUserRequestV1 request) {
        return null;
    }

    /**
     * Handle the patch user request.
     *
     * @param request The {@link PatchUserRequestV1}
     * @return The HTTP response includes the updated user.
     */
    public ResponseEntity<GetUserResponseV1> patch(@RequestBody PatchUserRequestV1 request) {
        return null;
    }

}
