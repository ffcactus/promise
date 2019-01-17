package com.huawei.skywalker.server.exception;

import com.promise.platform.sdk.exception.RestApiException;

public class ServerStateException extends RestApiException
{
    /**
     * 
     */
    private static final long serialVersionUID = 6941981587411425248L;
    private static final String errorCode = "api.error.message.server.state";
    
    /**
     * Represents the condition that the server state prohibit this operation.
     */
    public ServerStateException()
    {
        super(errorCode, "The state of server prohibit this operation", "make sure the server in the suitable state and try again");
    }

}
