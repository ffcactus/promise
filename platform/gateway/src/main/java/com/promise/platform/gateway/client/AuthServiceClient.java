package com.promise.platform.gateway.client;

import com.promise.platform.sdk.dto.auth.GetUserResponseV1;
import com.promise.platform.sdk.model.JwtUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(value = "promise-platform-auth")
public interface AuthServiceClient {

    @GetMapping("/api/v1/users/{id}")
    GetUserResponseV1 getUser(@PathVariable("id") Long id);

    @GetMapping("/api/v1/session")
    JwtUser parseToken(@RequestHeader("Authorization") String token);

}
