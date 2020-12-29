package com.promise.platform.devicemonitor.entity;

import com.promise.platform.devicemonitor.sdk.dto.ComponentStateRecordV1;
import com.promise.platform.devicemonitor.sdk.dto.DeviceStateRecordV1;

/**
 * A place to hold some static method for the convention from entity to dto or vice versa.
 */
public class EntityConverter {
    public static DeviceStateRecordV1 toDeviceStateRecord(DeviceStateRecord record) {
        var v1 = new DeviceStateRecordV1();
        v1.setConnection(record.getConnection());
        v1.setPower(record.getPower());
        v1.setHealth(record.getHealth());
        v1.setRecordAt(record.getRecordAt());
        return v1;
    }

    public static ComponentStateRecordV1 toComponentStateRecord(ComponentStateRecord record) {
        var v1 = new ComponentStateRecordV1();
        v1.setType(record.getComponentType());
        v1.setLocation(record.getLocation());
        v1.setInstall(record.getInstall());
        v1.setHealth(record.getHealth());
        v1.setRecordAt(record.getRecordAt());
        return v1;
    }
}
