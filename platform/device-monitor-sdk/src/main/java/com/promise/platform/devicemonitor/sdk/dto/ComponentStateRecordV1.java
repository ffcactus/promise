package com.promise.platform.devicemonitor.sdk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ComponentStateRecordV1 {
    private ComponentType type;
    private String location;
    private ComponentInstallState install;
    private HealthState health;
    private LocalDateTime recordAt;
}
