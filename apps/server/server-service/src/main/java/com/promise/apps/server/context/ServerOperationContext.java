package com.promise.apps.server.context;

import com.promise.apps.server.model.Server;
import com.promise.platform.common.model.AsynchResult;

/**
 * Represents the context of a server operation.
 */
public interface ServerOperationContext {
    /**
     * Get the original request.
     *
     * @return The original request.
     */
    //public R getRequest();

    /**
     * Get the current server.
     *
     * @return The server save in the context.
     */
    //public Server getServer();

    /**
     * Update the server in the database and then return it. If server argument
     * is null then just load the server from database.
     *
     * @param server The server that should be save to the database.
     * @return The updated server.
     */
    //public Server updateServer(Server server);

    /**
     * Set the expected server state after the context is terminated.
     *
     * @param state
     */
    //public void setExpectedState(ServerStateV1 state);

    /**
     * Get the expected server state.
     *
     * @return The expected server state.
     */
    //public ServerStateV1 getExpectedState();

    /**
     * Terminate the context, do the necessary operation after the termination.
     */
    //public void terminate();

    /**
     * Update the task step.
     * @param result
     */
    //public void updateTaskStep(UpdateTaskStepRequestV1 result);

    /**
     * Perform the jobs involved in this context.
     */
    public AsynchResult<Server> run();
}
