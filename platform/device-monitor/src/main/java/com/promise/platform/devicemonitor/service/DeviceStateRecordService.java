package com.promise.platform.devicemonitor.service;

import com.promise.platform.devicemonitor.entity.EntityConverter;
import com.promise.platform.devicemonitor.repository.ComponentStateRecordRepository;
import com.promise.platform.devicemonitor.repository.DeviceStateRecordRepository;
import com.promise.platform.devicemonitor.sdk.dto.GetDeviceStateRecordResponseV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class DeviceStateRecordService {

    @Autowired
    private DeviceStateRecordRepository deviceStateRecordRepository;

    @Autowired
    private ComponentStateRecordRepository componentStateRecordRepository;

    public GetDeviceStateRecordResponseV1 getDeviceStateRecord(Long id) {
        var deviceStateRecords = deviceStateRecordRepository.getAllByDeviceId(id);
        var componentStateRecords = componentStateRecordRepository.getAllByDeviceId(id);
        var ret = new GetDeviceStateRecordResponseV1();
        if (deviceStateRecords == null) {
            ret.setDeviceStateRecords(new ArrayList<>());
        } else {
            ret.setDeviceStateRecords(
                    deviceStateRecords.stream().map(EntityConverter::toDeviceStateRecord).collect(Collectors.toList())
            );
        }
        if (componentStateRecords == null) {
            ret.setDeviceStateRecords(new ArrayList<>());
        } else {
            ret.setComponentStateRecords(
                    componentStateRecords.stream().map(EntityConverter::toComponentStateRecord).collect(Collectors.toList())
            );
        }
        return ret;
    }
}
