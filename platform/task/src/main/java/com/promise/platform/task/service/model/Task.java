package com.promise.platform.task.service.model;

import com.promise.platform.common.model.ScopedResource;
import com.promise.platform.task.sdk.dto.ExecutionStateV1;
import com.promise.platform.task.sdk.dto.GetTaskResponseV1;
import com.promise.platform.task.sdk.dto.UpdateTaskRequestV1;
import com.promise.platform.task.sdk.dto.UpdateTaskStepRequestV1;
import com.promise.platform.task.service.exception.TaskStepNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the task.
 */
public class Task extends ScopedResource {
    public String messageId;
    public String description;
    public ExecutionStateV1 state;
    public String parentUri;
    public String createdByUri;
    public String targetUri;
    public String currentStep;
    public Integer expectedDuration;
    public Integer percentage;
    public List<TaskStep> steps;
    public ExecutionResult result;

    public Task() {
        steps = new ArrayList<TaskStep>();
        result = new ExecutionResult();
    }

    /**
     * Convert to response DTO.
     *
     * @return response DTO.
     */
    public GetTaskResponseV1 toResponseV1() {
        var ret = new GetTaskResponseV1();
        ret.id = this.id;
        ret.uri = this.uri;
        ret.name = this.name;
        ret.createdAt = this.createdAt;
        ret.updatedAt = this.updatedAt;
        ret.scopes = this.scopes;

        ret.description = this.description;
        ret.messageId = this.messageId;
        ret.state = this.state;
        ret.parentUri = this.parentUri;
        ret.createdByUri = this.createdByUri;
        ret.targetUri = this.targetUri;
        ret.expectedDuration = this.expectedDuration;
        ret.percentage = this.percentage;
        ret.steps = this.steps.stream().map(TaskStep::toResponseV1).collect(Collectors.toList());
        ret.result = this.result.toResponseV1();
        return ret;
    }

    /**
     * Update task according to update task request.
     *
     * @param request The update task request.
     */
    public void update(UpdateTaskRequestV1 request) {
        if (request.state != null) {
            this.state = request.state;
        }
        if (request.expectedDuration != null) {
            this.expectedDuration = request.expectedDuration;
        }
        if (request.percentage != null) {
            this.percentage = request.percentage;
        }
        if (request.result != null) {
            this.result.update(request.result);
        }
    }

    /**
     * Update task step according to update task step request.
     *
     * @param request The update task step request.
     * @throws TaskStepNotFoundException When the task step not found.
     */
    public void updateStep(UpdateTaskStepRequestV1 request)
            throws TaskStepNotFoundException {
        var currentTime = 0;
        var foundStep = false;
        for (var step : this.steps) {
            currentTime += step.expectedDuration;
            if (step.name.equals(request.name)) {
                foundStep = true;
                if (request.state != null) {
                    // whenever the use update a task step we think the current step is this one.
                    this.currentStep = step.name;
                    step.state = request.state;
                    switch (request.state) {
                        case Ready:
                        case Running:
                        case Suspended:
                            currentTime -= step.expectedDuration;
                        default:
                            break;
                    }
                }
                if (request.result != null) {
                    step.result.update(request.result);
                }
                var percentage = (float) currentTime / (float) this.expectedDuration;
                this.percentage = (int) (percentage * 100 + 0.5);
                if (this.percentage > 100) {
                    this.percentage = 100;
                }
                break;
            }
        }
        if (!foundStep) {
            throw new TaskStepNotFoundException();
        }
    }
}
