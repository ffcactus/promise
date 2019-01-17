package com.promise.platform.task.sdk.dto;

/**
 * Represents a execution state.
 *
 */
public enum ExecutionStateV1
{
    /**
     * The execution is ready but haven't run yet.
     */
    Ready,

    /**
     * The execution is running.
     */
    Running,

    /**
     * The execution has been suspended.
     */
    Suspended,

    /**
     * The execution is terminated.
     */
    Terminated,
}
