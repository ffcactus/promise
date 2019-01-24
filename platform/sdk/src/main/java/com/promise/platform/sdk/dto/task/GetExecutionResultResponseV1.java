package com.promise.platform.sdk.dto.task;

import com.promise.platform.sdk.dto.ErrorMessageResponseV1;

import lombok.Data;

/**
 * Represents the execution result.
 *
 */
@Data
public class GetExecutionResultResponseV1
{
    public ExecutionResultStateV1 state;
    public ErrorMessageResponseV1 message;

    public GetExecutionResultResponseV1()
    {
        message = new ErrorMessageResponseV1();
    }
}
