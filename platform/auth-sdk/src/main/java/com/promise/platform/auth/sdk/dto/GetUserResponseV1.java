package com.promise.platform.auth.sdk.dto;

import lombok.Data;

/**
 * Represents the response body of User.
 */
@Data
public class GetUserResponseV1 {
    private Long id;
    private String username;
    private String email;
    private String companyUri;
    private String description;
    private String role;
}
