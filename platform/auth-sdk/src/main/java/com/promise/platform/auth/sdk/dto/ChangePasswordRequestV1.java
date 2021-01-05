package com.promise.platform.auth.sdk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangePasswordRequestV1 {
    private String oldPassword;
    private String newPassword;
}
