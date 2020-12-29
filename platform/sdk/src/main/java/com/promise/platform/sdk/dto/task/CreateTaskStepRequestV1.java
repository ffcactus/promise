package com.promise.platform.sdk.dto.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The request body to create a task step.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskStepRequestV1 {
    public String messageId;
    public String name;
    public String description;
    public Integer expectedDuration;
}
