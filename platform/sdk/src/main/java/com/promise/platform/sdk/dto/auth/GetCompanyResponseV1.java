package com.promise.platform.sdk.dto.auth;

import java.util.List;

import com.promise.platform.sdk.dto.BasicResourceResponseV1;

/**
 * Represents the response of get company.
 *
 */
public class GetCompanyResponseV1 extends BasicResourceResponseV1
{
    public List<String> organizationUris;
}
