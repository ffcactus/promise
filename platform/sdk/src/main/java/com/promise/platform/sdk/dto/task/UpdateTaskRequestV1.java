package com.promise.platform.sdk.dto.task;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the request body of update task.
 */
@Data
@NoArgsConstructor
public class UpdateTaskRequestV1 {
    public ExecutionStateV1 state;
    public Integer expectedDuration;
    public Integer percentage;
    public UpdateExecutionResultRequestV1 result;
}
