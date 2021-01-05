package com.promise.platform.task.service.exception;

import com.promise.platform.common.exception.RestApiException;

public class TaskStepNotFoundException extends RestApiException {
    /**
     *
     */
    private static final long serialVersionUID = -7254126179510384764L;

    public TaskStepNotFoundException() {
        super("api.error.message.task.StepNotFound", "Task step not found.", "Specify the correct task step name and try again.");
    }
}
