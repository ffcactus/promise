package com.promise.platform.sdk.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangePasswordRequestV1 {
    private String oldPassword;
    private String newPassword;
}
