package com.promise.platform.auth.sdk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"password"})
public class RegisterUserRequestV1 {
    public String username;
    public String password;
    public String email;
}
