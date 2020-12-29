package com.promise.platform.devicebasic.sdk.message;

/**
 * All the possible command from upper stream or collector.
 */
public enum MessageCommand {
    Success,
    Failure,

    // The message sending to collector.
    DeviceDiscover,
    DeviceMonitor,
    DevicePowerOn,
    DevicePowerOff,
    DevicePowerReset,
    DeviceGetAsset,
    DeviceGetPowerConsumption,

    // The message sending to upper stream.
    CollectorRegister,
}
