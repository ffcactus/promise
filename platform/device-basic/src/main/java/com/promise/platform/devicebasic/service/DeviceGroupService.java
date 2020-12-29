package com.promise.platform.devicebasic.service;

import com.promise.platform.common.dto.ListRequestV1;
import com.promise.platform.common.dto.ListResponseV1;
import com.promise.platform.common.util.PagingUtils;
import com.promise.platform.devicebasic.entity.EntityConverter;
import com.promise.platform.devicebasic.repository.DeviceGroupRepository;
import com.promise.platform.devicebasic.sdk.dto.devicegroup.CreateDeviceGroupRequestV1;
import com.promise.platform.devicebasic.sdk.dto.devicegroup.GetDeviceGroupListItemV1;
import com.promise.platform.devicebasic.sdk.dto.devicegroup.GetDeviceGroupResponseV1;
import com.promise.platform.devicebasic.sdk.dto.devicegroup.UpdateDeviceGroupRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeviceGroupService {

    @Autowired
    private DeviceGroupRepository deviceGroupRepository;

    /**
     * Create device group.
     * @param request create device group request.
     * @return the device group created.
     */
    public GetDeviceGroupResponseV1 create(CreateDeviceGroupRequestV1 request) {
        var entity = EntityConverter.toDeviceGroupEntity(request);
        entity = deviceGroupRepository.save(entity);

        return EntityConverter.toDeviceGroupResponse(entity);
    }

    public GetDeviceGroupResponseV1 getById(Long id) {
        return EntityConverter.toDeviceGroupResponse(
                deviceGroupRepository.findById(id).orElseThrow()
        );
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public GetDeviceGroupResponseV1 deleteById(Long id) {
        var entity = deviceGroupRepository.findById(id).orElseThrow();
        deviceGroupRepository.deleteById(id);
        return EntityConverter.toDeviceGroupResponse(entity);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public GetDeviceGroupResponseV1 updateById(Long id, UpdateDeviceGroupRequestV1 request) {
        var entity = deviceGroupRepository.findById(id).orElseThrow();
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        return EntityConverter.toDeviceGroupResponse(deviceGroupRepository.save(entity));
    }

    public ListResponseV1<GetDeviceGroupListItemV1> getAll(ListRequestV1 request) {
        return PagingUtils.from(
                deviceGroupRepository.findAll(PagingUtils.from(request)),
                EntityConverter::toDeviceGroupListItem
        );
    }
}
