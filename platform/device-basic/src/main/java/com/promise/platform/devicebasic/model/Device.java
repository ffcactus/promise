package com.promise.platform.devicebasic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Represents the device object in the system.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Device {
    private String id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private DeviceLifeCycleState lifeCycleState;
}
