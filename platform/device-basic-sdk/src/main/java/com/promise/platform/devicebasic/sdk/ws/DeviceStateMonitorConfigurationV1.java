package com.promise.platform.devicebasic.sdk.ws;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The configuration about how to monitor the state of a device.
 */
@Data
@NoArgsConstructor
public class DeviceStateMonitorConfigurationV1 {
    Long interval;
}
