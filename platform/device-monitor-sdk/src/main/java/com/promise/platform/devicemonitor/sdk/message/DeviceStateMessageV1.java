package com.promise.platform.devicemonitor.sdk.message;

import com.promise.platform.devicemonitor.sdk.dto.DeviceConnectionState;
import com.promise.platform.devicemonitor.sdk.dto.DeviceMonitorMessageType;
import com.promise.platform.devicemonitor.sdk.dto.DevicePowerState;
import com.promise.platform.devicemonitor.sdk.dto.HealthState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The payload then the {@link DeviceMonitorMessageV1} is {@link DeviceMonitorMessageType#DeviceStateMessage}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceStateMessageV1 {
    private DeviceConnectionState connection;
    private DevicePowerState power;
    private HealthState health;

    @Override
    public String toString() {
        return "DeviceStateMessageV1{" +
                "connection=" + connection +
                ", power=" + power +
                ", health=" + health +
                '}';
    }
}