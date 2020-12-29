package com.promise.platform.sdk.dto.company;

import com.promise.platform.sdk.dto.BasicResourceResponseV1;

import java.util.List;

/**
 * Represents the response of get company.
 */
public class GetCompanyResponseV1 extends BasicResourceResponseV1 {
    public List<String> organizationUris;
}
