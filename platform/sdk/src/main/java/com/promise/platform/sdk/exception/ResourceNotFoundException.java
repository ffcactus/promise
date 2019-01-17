package com.promise.platform.sdk.exception;

public class ResourceNotFoundException extends RestApiException
{
    /**
     * 
     */
    private static final long serialVersionUID = -780372475005439786L;

    public ResourceNotFoundException()
    {
        super("api.error.message.common.ResourceNotFound", "Can't find the resource.", "Specify the correct ID and try again.");
    }

}
