package com.promise.platform.auth.sdk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The login request body.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestV1 {
    public String username;
    public String password;
}
