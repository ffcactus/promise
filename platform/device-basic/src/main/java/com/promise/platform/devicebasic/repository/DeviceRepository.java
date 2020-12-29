package com.promise.platform.devicebasic.repository;

import com.promise.platform.devicebasic.entity.DeviceEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface DeviceRepository extends PagingAndSortingRepository<DeviceEntity, Long> {
    Optional<DeviceEntity> findBySerialNumber(String serialNumber);
}
