package com.promise.platform.devicemonitor.sdk.message;

import com.promise.platform.devicemonitor.sdk.dto.DeviceMonitorMessageType;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The monitor message sending from collector.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceMonitorMessageV1 {
    private Long id;
    private DeviceMonitorMessageType type;
    private JsonNode payload;

    @Override
    public String toString() {
        return "DeviceMonitorMessageV1{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}
