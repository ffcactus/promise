package com.promise.platform.devicebasic.service;

import com.promise.platform.devicebasic.repository.DeviceGroupMemberRepository;
import com.promise.platform.devicebasic.repository.DeviceGroupRepository;
import com.promise.platform.devicebasic.repository.DgCgMappingRepository;
import com.promise.platform.devicebasic.sdk.dto.devicegroup.DeviceGroupType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class AdviceQueryService {

    @Autowired
    private DeviceGroupMemberRepository deviceGroupMemberRepository;

    @Autowired
    private DeviceGroupRepository deviceGroupRepository;

    @Autowired
    private DgCgMappingRepository dgCgMappingRepository;

    public Long getCollectorGroupIdByDeviceId(Long deviceId) {
        // get device's groups.
        var deviceGroupIds = deviceGroupMemberRepository.findByRightId(deviceId);
        // get all the device group in which the type is Collector.
        var deviceGroups = deviceGroupRepository.findAllByType(DeviceGroupType.Collector);

        var deviceGroupId = -1L;
        // A device can only belongs to a group of which the type is Collector.
        for (var m : deviceGroupIds) {
            for (var deviceGroup : deviceGroups) {
                if (deviceGroup.getId().equals(m.getLeftId())) {
                    deviceGroupId = deviceGroup.getId();
                    break;
                }
            }
        }
        if (deviceGroupId == -1L) {
            throw new NoSuchElementException();
        }
        var dgCgMapping = dgCgMappingRepository.findByLeftId(deviceGroupId);
        if (dgCgMapping == null) {
            throw new NoSuchElementException();
        }
        return dgCgMapping.getRightId();
    }
}
