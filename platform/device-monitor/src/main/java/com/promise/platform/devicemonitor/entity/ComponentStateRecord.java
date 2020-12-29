package com.promise.platform.devicemonitor.entity;

import com.promise.platform.devicemonitor.sdk.dto.ComponentInstallState;
import com.promise.platform.devicemonitor.sdk.dto.ComponentType;
import com.promise.platform.devicemonitor.sdk.dto.HealthState;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(indexes = @Index(columnList = "deviceId, componentType, recordAt"))
public class ComponentStateRecord implements Comparable<ComponentStateRecord> {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private Long deviceId;
    @Enumerated(EnumType.STRING)
    private ComponentType componentType;
    private String location;
    @Enumerated(EnumType.STRING)
    private ComponentInstallState install;
    @Enumerated(EnumType.STRING)
    private HealthState health;
    private LocalDateTime recordAt;

    public ComponentStateRecord(Long deviceId, ComponentType componentType, String location, ComponentInstallState install, HealthState health) {
        this.deviceId = deviceId;
        this.componentType = componentType;
        this.location = location;
        this.install = install;
        this.health = health;
    }

    @PrePersist
    public void prePersist() {
        recordAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "ComponentStateRecord{" +
                "id=" + id +
                ", deviceId=" + deviceId +
                ", componentType=" + componentType +
                ", location=" + location +
                ", install=" + install +
                ", health=" + health +
                '}';
    }


    @Override
    public int compareTo(ComponentStateRecord o) {
        return recordAt.compareTo(o.recordAt);
    }
}
