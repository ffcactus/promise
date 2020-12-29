package com.promise.platform.devicemonitor.sdk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DeviceStateRecordV1 {
    private DeviceConnectionState connection;
    private DevicePowerState power;
    private HealthState health;
    private LocalDateTime recordAt;
}
