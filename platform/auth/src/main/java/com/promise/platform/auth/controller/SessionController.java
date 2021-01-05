package com.promise.platform.auth.controller;

import com.promise.platform.auth.sdk.dto.LoginRequestV1;
import com.promise.platform.auth.sdk.exception.UnauthorizedException;
import com.promise.platform.auth.sdk.jwt.JwtUser;
import com.promise.platform.auth.service.SessionService;
import com.promise.platform.common.controller.CommonExceptionController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

/**
 * The controller for login and logout.
 */
@RestController
@RequestMapping("/api/v1/rest/session")
public class SessionController extends CommonExceptionController {
    @Autowired
    private SessionService service;

    /**
     * Handle login process.
     *
     * @param request The login request
     * @return The HTTP response that includes the access token in the head and
     * access token information in the response body.
     */
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody final LoginRequestV1 request) {
        String token = service.Login(request);

        return ResponseEntity.noContent().headers(tokenHeaders(token)).build();
    }

    @PostMapping("/logout")
    public void logout(@Nullable @RequestHeader("Authorization") String token) {
        service.Logout(removeBearer(token));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Void> refreshToken(@RequestHeader("Authorization") String token) {
        String newToken = service.refreshToken(removeBearer(token));

        return ResponseEntity.noContent().headers(tokenHeaders(newToken)).build();
    }

    // TODO remove this interface?
    @GetMapping
    public JwtUser parseToken(@Nullable @RequestHeader("Authorization") String token) {
        return service.parseToken(removeBearer(token));
    }

    @DeleteMapping("/{id}")
    public void deleteAllTokens(@PathVariable("id") Long userId) {
        service.deleteTokens(userId);
    }

    private HttpHeaders tokenHeaders(String token) {
        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        responseHeaders.set("Authorization", "Bearer " + token);

        return responseHeaders;
    }

    private String removeBearer(String authorization) {
        if (!StringUtils.startsWith(authorization, "Bearer")) {
            throw new UnauthorizedException();
        }

        return StringUtils.substring(authorization, "Bearer ".length());
    }

//	@GetMapping("/user-info")
//	public ResponseEntity<GetUserResponseV1> info(@RequestHeader("Authorization") String token) {
//		Optional<User> user = service.info(token);
//		return new ResponseEntity<GetUserResponseV1>(user.get().toResponseV1(), HttpStatus.OK);
//	}
}
