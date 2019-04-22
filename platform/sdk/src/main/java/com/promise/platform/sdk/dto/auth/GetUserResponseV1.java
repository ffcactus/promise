package com.promise.platform.sdk.dto.auth;

import lombok.Data;

/**
 * Represents the response body of User.
 *
 */
@Data
public class GetUserResponseV1
{
    public String id;
    public String username;
    public String email;
    public String companyUri;
}
