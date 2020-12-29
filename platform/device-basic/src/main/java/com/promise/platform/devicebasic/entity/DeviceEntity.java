package com.promise.platform.devicebasic.entity;

import com.promise.platform.common.URIs;
import com.promise.platform.common.entity.ResourceEntity;
import com.promise.platform.devicebasic.sdk.dto.device.DeviceType;
import com.promise.platform.devicebasic.sdk.model.DeviceHardwareType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Device represents the device instance in the database.
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class DeviceEntity extends ResourceEntity {
    @Enumerated(EnumType.STRING)
    private DeviceType type;
    private String vendor;
    private String model;
    private String subModel;
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private DeviceHardwareType dht;
    private Long discoverRequestId;

    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "device")
    private DiscoverDeviceRequestEntity discoverRequest;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "device")
    private List<ProcessorEntity> processors;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "device")
    private List<MemoryEntity> memory;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "device")
    private List<DriveEntity> drives;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "device")
    private List<PowerSupplyEntity> powerSupplies;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "device")
    private List<FanEntity> fans;

    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "device")
    private List<CardEntity> cards;

    public String getUri() {
        return URIs.DeviceBaseUri + "/" + super.getId();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
