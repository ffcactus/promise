package com.promise.platform.sdk.dto.task;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * The request body to create the task.
 */
@Data
@AllArgsConstructor
public class CreateTaskRequestV1 {
    public String messageId;
    public String name;
    public String description;
    public String parentUri;
    public String createdByUri;
    public String targetUri;
    public List<CreateTaskStepRequestV1> steps;

    public CreateTaskRequestV1() {
        steps = new ArrayList<CreateTaskStepRequestV1>();
    }
}
