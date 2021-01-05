package com.promise.platform.task.sdk.dto;

import com.promise.platform.common.dto.ScopedResourceResponseV1;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the GET task response body.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GetTaskResponseV1 extends ScopedResourceResponseV1 {
    public String messageId;
    public String description;
    public ExecutionStateV1 state;
    public String parentUri;
    public String createdByUri;
    public String targetUri;
    public Integer expectedDuration;
    public Integer percentage;
    public String currentStep;
    public List<GetTaskStepResponseV1> steps;
    public GetExecutionResultResponseV1 result;

    public GetTaskResponseV1() {
        steps = new ArrayList<GetTaskStepResponseV1>();
        result = new GetExecutionResultResponseV1();
    }
}
