package com.promise.platform.devicebasic.sdk.ws;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Configuration about how to monitor a component's state.
 */
@Data
@NoArgsConstructor
public class ComponentStateMonitorConfigurationV1 {
    Long interval;
}
