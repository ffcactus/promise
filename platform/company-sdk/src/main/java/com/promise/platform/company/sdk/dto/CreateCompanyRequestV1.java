package com.promise.platform.company.sdk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the request body of create company.
 */
@Data
@NoArgsConstructor
public class CreateCompanyRequestV1 {
    public String name;
}
