package com.promise.platform.gateway.client;

import com.promise.platform.auth.sdk.dto.GetUserResponseV1;
import com.promise.platform.auth.sdk.jwt.JwtUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(value = "promise-platform-auth")
public interface AuthServiceClient {

    @GetMapping("/api/v1/rest/users/{id}")
    GetUserResponseV1 getUser(@PathVariable("id") Long id);

    @GetMapping("/api/v1/rest/session")
    JwtUser parseToken(@RequestHeader("Authorization") String token);
}
