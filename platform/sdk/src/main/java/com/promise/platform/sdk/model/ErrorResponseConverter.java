package com.promise.platform.sdk.model;

import com.promise.platform.sdk.dto.ErrorMessageResponseV1;

public interface ErrorResponseConverter {
    public ErrorMessageResponseV1 convertToErrorResponse();
}
