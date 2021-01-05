package com.promise.platform.task.sdk.dto;

import com.promise.platform.common.dto.ErrorResponseV1;
import com.promise.platform.common.model.ErrorMessage;
import lombok.Data;

/**
 * Represents the execution result.
 */
@Data
public class GetExecutionResultResponseV1 {
    public ExecutionResultStateV1 state;
    public ErrorMessage message;

    public GetExecutionResultResponseV1() {
        message = new ErrorMessage();
    }
}
