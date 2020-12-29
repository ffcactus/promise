package com.promise.platform.devicebasic.sdk.ws;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AttributeMonitorConfigurationV1 {
    Long interval;
    Long increaseCriticalThreshold;
    Long increaseWarningThreshold;
    Long decreaseWarningThreshold;
    Long decreaseCriticalThreshold;
}
