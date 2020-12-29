package com.promise.platform.devicebasic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class MemoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String location;
    private String manufacturer;
    private String model;
    private String serialNumber;
    private String partNumber;
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private DeviceEntity device;
}
