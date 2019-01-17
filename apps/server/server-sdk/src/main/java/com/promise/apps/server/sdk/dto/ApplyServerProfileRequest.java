package com.promise.apps.server.sdk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The apply server profile request.
 *
 */
@Data
@NoArgsConstructor
public class ApplyServerProfileRequest
{
    private String profileUri;
}
