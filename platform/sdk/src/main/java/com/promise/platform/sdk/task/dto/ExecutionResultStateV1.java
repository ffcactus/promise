package com.promise.platform.sdk.task.dto;

public enum ExecutionResultStateV1
{
    /**
     * The execution result is unknown yet.
     */
    Unknown,

    /**
     * The execution is abort, and it can be taken as no result.
     */
    Abort,

    /**
     * The execution can be taken as finished although there are some warnings.
     */
    Warning,

    /**
     * The execution can be taken as failed with error.
     */
    Error,

    /**
     * The execution is finished as is expected.
     */
    Finished,
}
