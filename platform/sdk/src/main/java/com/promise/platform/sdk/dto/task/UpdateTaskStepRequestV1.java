package com.promise.platform.sdk.dto.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the request body of update task step.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskStepRequestV1
{
    public String name;
    public ExecutionStateV1 state;
    public UpdateExecutionResultRequestV1 result;
}
