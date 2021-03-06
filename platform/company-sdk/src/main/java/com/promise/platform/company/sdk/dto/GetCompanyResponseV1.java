package com.promise.platform.company.sdk.dto;

import com.promise.platform.common.dto.BasicResourceResponseV1;

import java.util.List;

/**
 * Represents the response of get company.
 */
public class GetCompanyResponseV1 extends BasicResourceResponseV1 {
    public List<String> organizationUris;
}
