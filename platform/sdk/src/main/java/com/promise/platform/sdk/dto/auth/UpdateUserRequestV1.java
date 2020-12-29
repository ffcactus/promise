package com.promise.platform.sdk.dto.auth;

import lombok.Data;

@Data
public class UpdateUserRequestV1 {
    private String username;
    private String email;
    private String companyUri;
    private String description;
    private String role;
}
