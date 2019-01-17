package com.promise.apps.server.sdk.dto;

/**
 * Represents the server's health condition, it totally depends the current alarms on the server.
 *
 */
public enum ServerHealthV1
{
    /**
     * Indicate the server has no alarm.
     */
    OK,
    
    /**
     * Indicate the server has warning level alarm.
     */
    Warning,
    
    /**
     * Indicate the server has critical level alarm.
     */
    Critical,
}
