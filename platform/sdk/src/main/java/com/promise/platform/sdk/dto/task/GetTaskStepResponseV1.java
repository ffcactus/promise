package com.promise.platform.sdk.dto.task;

import lombok.Data;

/**
 * Represents the a step in a task.
 *
 * @author b00392874
 */
@Data
public class GetTaskStepResponseV1 {
    public String messageId;
    public String name;
    public String description;
    public Integer expectedDuration;
    public ExecutionStateV1 state;
    public GetExecutionResultResponseV1 result;

    public GetTaskStepResponseV1() {
        result = new GetExecutionResultResponseV1();
    }
}
