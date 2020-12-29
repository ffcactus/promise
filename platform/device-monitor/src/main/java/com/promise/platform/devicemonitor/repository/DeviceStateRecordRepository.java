package com.promise.platform.devicemonitor.repository;

import com.promise.platform.devicemonitor.entity.DeviceStateRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeviceStateRecordRepository extends CrudRepository<DeviceStateRecord, Long> {
    List<DeviceStateRecord> getAllByDeviceId(Long id);
}
