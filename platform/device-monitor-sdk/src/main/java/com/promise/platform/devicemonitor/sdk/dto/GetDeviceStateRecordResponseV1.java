package com.promise.platform.devicemonitor.sdk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GetDeviceStateRecordResponseV1 {
    private List<DeviceStateRecordV1> deviceStateRecords;
    private List<ComponentStateRecordV1> componentStateRecords;
}
