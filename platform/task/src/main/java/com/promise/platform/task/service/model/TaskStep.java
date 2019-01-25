package com.promise.platform.task.service.model;

import com.promise.platform.sdk.dto.task.CreateTaskStepRequestV1;
import com.promise.platform.sdk.dto.task.ExecutionStateV1;
import com.promise.platform.sdk.dto.task.GetTaskStepResponseV1;

public class TaskStep
{
    public String messageId;
    public String name;
    public String description;
    public Integer expectedDuration;
    public ExecutionStateV1 state;
    public ExecutionResult result;

    public TaskStep()
    {
        result = new ExecutionResult();
    }

    /**
     * Default constructor.
     */
    public TaskStep(CreateTaskStepRequestV1 request)
    {
        this.messageId = request.messageId;
        this.name = request.name;
        this.description = request.description;
        this.expectedDuration = request.expectedDuration;
        this.state = ExecutionStateV1.Ready;
        this.result = new ExecutionResult();
    }

    /**
     * Convert to response DTO.
     * 
     * @return response DTO.
     */
    public GetTaskStepResponseV1 toResponseV1()
    {
        var ret = new GetTaskStepResponseV1();
        ret.messageId = this.messageId;
        ret.name = this.name;
        ret.description = this.description;
        ret.expectedDuration = this.expectedDuration;
        ret.state = this.state;
        ret.result = this.result.toResponseV1();
        return ret;
    }
}
