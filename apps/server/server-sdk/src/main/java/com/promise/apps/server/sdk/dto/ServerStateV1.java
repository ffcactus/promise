package com.promise.apps.server.sdk.dto;

/**
 * The possible server state.
 *
 */
public enum ServerStateV1
{
    /**
     * The server is initialized but the server hasn't connected to the system yet.
     */
    Initialized,
    
    /**
     * The server is ready to accept operations from users.
     */
    Ready,
    
    /**
     * The server is locked for business transaction.
     */
    Locked,
    
    /**
     * The server remain in the system but not under system's management.
     */
    Unmanaged,
}
