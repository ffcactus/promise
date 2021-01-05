package com.promise.platform.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * For asynchronous operation we need return task, but sometimes we also need
 * return the initialized resource. For example, when you add a server, it is
 * enough to just return the task, the initialized server with the ID should
 * also be returned, or you don't know how to find the server later.
 *
 * @param <R> The initialized resource.
 */
@Data
@NoArgsConstructor
public class AsynchResult<R> {
    public String taskUri;
    public R result;

    public AsynchResult(String taskUri, R result) {
        this.taskUri = taskUri;
        this.result = result;
    }
}
