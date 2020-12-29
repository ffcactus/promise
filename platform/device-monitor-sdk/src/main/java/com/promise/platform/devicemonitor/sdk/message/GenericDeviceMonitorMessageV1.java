package com.promise.platform.devicemonitor.sdk.message;

import com.promise.platform.devicemonitor.sdk.dto.DeviceMonitorMessageType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The monitor message sending to device monitor.
 * @param <T> the payload type.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericDeviceMonitorMessageV1<T> {
    private Long id;
    private DeviceMonitorMessageType type;
    private T payload;

    @Override
    public String toString() {
        return "GenericDeviceMonitorMessageV1{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
