package com.promise.platform.devicemonitor.sdk.message;

import com.promise.platform.devicemonitor.sdk.dto.ComponentInstallState;
import com.promise.platform.devicemonitor.sdk.dto.ComponentType;
import com.promise.platform.devicemonitor.sdk.dto.DeviceMonitorMessageType;
import com.promise.platform.devicemonitor.sdk.dto.HealthState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The payload then the {@link DeviceMonitorMessageV1} is {@link DeviceMonitorMessageType#ComponentStateMessage}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComponentStateMessageV1 {
    private ComponentType type;
    private String location;
    private ComponentInstallState install;
    private HealthState health;

    @Override
    public String toString() {
        return "ComponentStateMessageV1{" +
                "type=" + type +
                ", location=" + location +
                ", install=" + install +
                ", health=" + health +
                '}';
    }
}
