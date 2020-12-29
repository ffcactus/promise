package com.promise.platform.devicemonitor.repository;

import com.promise.platform.devicemonitor.entity.ComponentStateRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComponentStateRecordRepository extends CrudRepository<ComponentStateRecord, Long> {
    List<ComponentStateRecord> getAllByDeviceId(Long id);
}
