package com.promise.platform.devicebasic.repository;

import com.promise.platform.devicebasic.entity.DeviceGroupEntity;
import com.promise.platform.devicebasic.sdk.dto.devicegroup.DeviceGroupType;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DeviceGroupRepository extends PagingAndSortingRepository<DeviceGroupEntity, Long> {
    List<DeviceGroupEntity> findAllByType(DeviceGroupType type);
}
