package com.promise.platform.task.sdk.dto;

public class TaskResponse<R> {
    private String taskURL;
    private R body;

    public String getTaskUrl() {
        return taskURL;
    }

    public R getBody() {
        return body;
    }
}
