package com.promise.platform.devicemonitor.entity;

import com.promise.platform.devicemonitor.sdk.dto.DeviceConnectionState;
import com.promise.platform.devicemonitor.sdk.dto.DevicePowerState;
import com.promise.platform.devicemonitor.sdk.dto.HealthState;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(indexes = @Index(columnList = "deviceId, recordAt"))
public class DeviceStateRecord implements Comparable<DeviceStateRecord> {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private Long deviceId;
    @Enumerated(EnumType.STRING)
    private DeviceConnectionState connection;
    @Enumerated(EnumType.STRING)
    private DevicePowerState power;
    @Enumerated(EnumType.STRING)
    private HealthState health;
    private LocalDateTime recordAt;

    public DeviceStateRecord(Long deviceId, DeviceConnectionState connection, DevicePowerState power, HealthState health) {
        this.deviceId = deviceId;
        this.connection = connection;
        this.power = power;
        this.health = health;
    }

    @PrePersist
    public void prePersist() {
        recordAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "DeviceStateRecord{" +
                "id=" + id +
                ", deviceId=" + deviceId +
                ", connection=" + connection +
                ", power=" + power +
                '}';
    }

    @Override
    public int compareTo(DeviceStateRecord o) {
        return recordAt.compareTo(o.recordAt);
    }
}