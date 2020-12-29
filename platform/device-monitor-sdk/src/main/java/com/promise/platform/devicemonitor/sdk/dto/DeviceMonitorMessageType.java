package com.promise.platform.devicemonitor.sdk.dto;

/**
 * The message type of which the collector may sent.
 * <p/>
 * This can be used to parse the payload accordingly.
 */
public enum DeviceMonitorMessageType {
    DeviceStateMessage,
    ComponentStateMessage,
}
