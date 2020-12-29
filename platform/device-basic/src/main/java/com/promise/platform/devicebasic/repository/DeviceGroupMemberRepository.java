package com.promise.platform.devicebasic.repository;

import com.promise.platform.devicebasic.entity.DeviceGroupMemberEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface DeviceGroupMemberRepository extends PagingAndSortingRepository<DeviceGroupMemberEntity, Long> {
    DeviceGroupMemberEntity findFirstByLeftIdAndRightId(Long left, Long right);
    List<DeviceGroupMemberEntity> findByLeftId(Long left, Pageable pageable);
    List<DeviceGroupMemberEntity> findByRightId(Long right);
    List<DeviceGroupMemberEntity> findByLeftId(Long left);
    void deleteByRightId(Long right);
}
