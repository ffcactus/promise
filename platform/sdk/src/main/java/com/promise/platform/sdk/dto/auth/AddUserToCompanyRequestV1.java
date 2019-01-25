package com.promise.platform.sdk.dto.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the request to add a user to a company.
 */
@Data
@NoArgsConstructor
public class AddUserToCompanyRequestV1
{
    public String companyUri;
}
