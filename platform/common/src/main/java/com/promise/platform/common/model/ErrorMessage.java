package com.promise.platform.common.model;

import com.promise.platform.common.dto.ErrorMessageResponseV1;
import com.promise.platform.common.dto.UpdateErrorMessageRequestV1;

import java.util.List;

/**
 * Represents error response.
 */
public class ErrorMessage {
    public String errorCode;
    public String message;
    public List<String> messageArgs;

    /**
     * Convert to response DTO.
     *
     * @return response DTO.
     */
    public ErrorMessage toResponseV1() {
        var ret = new ErrorMessage();
        ret.errorCode = this.errorCode;
        ret.message = this.message;
        ret.messageArgs = this.messageArgs;
        return ret;
    }

    /**
     * Update from update request.
     *
     * @param request The update request.
     */
    public void update(UpdateErrorMessageRequestV1 request) {
        this.errorCode = request.errorCode;
        this.message = request.message;
        this.messageArgs = request.messageArgs;
    }
}
