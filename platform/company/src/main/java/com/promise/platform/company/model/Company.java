package com.promise.platform.company.model;

import com.promise.platform.common.model.BasicResource;
import com.promise.platform.company.sdk.dto.CreateCompanyRequestV1;
import com.promise.platform.company.sdk.dto.GetCompanyResponseV1;

/**
 * Represents the company.
 */
public class Company extends BasicResource {

    /**
     * Create the company from request.
     *
     * @param request The create company request.
     */
    public Company(CreateCompanyRequestV1 request) {
        BasicResource.Init(this, "/api/v1/auth/companies", request.name);
    }

    /**
     * Convert to response DTO.
     *
     * @return response DTO.
     */
    public GetCompanyResponseV1 toResponseV1() {
        final var ret = new GetCompanyResponseV1();
        BasicResource.model2dto(ret, this);
        return ret;
    }
}
