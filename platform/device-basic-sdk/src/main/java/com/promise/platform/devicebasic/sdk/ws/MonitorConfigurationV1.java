package com.promise.platform.devicebasic.sdk.ws;

import com.promise.platform.devicemonitor.sdk.dto.ComponentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Contains configurations for how to monitor device's state and readings.
 */
@Data
@NoArgsConstructor
public class MonitorConfigurationV1 {
    private Map<String, AttributeMonitorConfigurationV1> reading;
    private DeviceStateMonitorConfigurationV1 deviceState;
    private Map<ComponentType, ComponentStateMonitorConfigurationV1> componentState;
}
