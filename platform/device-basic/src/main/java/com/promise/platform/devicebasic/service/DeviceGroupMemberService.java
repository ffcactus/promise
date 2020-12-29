package com.promise.platform.devicebasic.service;

import com.promise.platform.common.dto.*;
import com.promise.platform.common.entity.MappingEntity;
import com.promise.platform.common.util.PagingUtils;
import com.promise.platform.devicebasic.entity.DeviceGroupMemberEntity;
import com.promise.platform.devicebasic.entity.EntityConverter;
import com.promise.platform.devicebasic.repository.DeviceGroupMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.stream.Collectors;

@Service
public class DeviceGroupMemberService {

    @Autowired
    private DeviceGroupMemberRepository deviceGroupMemberRepository;

    @Transactional
    public GroupMemberUpdateResponseV1 bulkUpdate(Long id, GroupMemberUpdateRequestV1 request) {
        var all = deviceGroupMemberRepository.findByLeftId(id);
        var allMembers = all.stream().map(MappingEntity::getRightId).collect(Collectors.toList());
        var results = new HashMap<Long, GroupMemberUpdateResult>();
        request.getUpdates().forEach((memberId, operation) -> {
            switch (operation) {
                case Add:
                    if (allMembers.contains(memberId)) {
                        results.put(memberId, GroupMemberUpdateResult.AlreadyExist);
                    } else {
                        deviceGroupMemberRepository.save(new DeviceGroupMemberEntity(id, memberId));
                        results.put(memberId, GroupMemberUpdateResult.Success);
                    }
                    break;
                case Remove:
                    if (!allMembers.contains(memberId)) {
                        results.put(memberId, GroupMemberUpdateResult.NoSuchElement);
                    } else {
                        deviceGroupMemberRepository.deleteByRightId(memberId);
                        results.put(memberId, GroupMemberUpdateResult.Success);
                    }
                    break;
                default:
                    results.put(memberId, GroupMemberUpdateResult.Failure);
                    break;
            }
        });
        return new GroupMemberUpdateResponseV1(results);
    }

    public ListResponseV1<MappingListItemV1> getAll(ListRequestV1 request) {
        return PagingUtils.from(
                deviceGroupMemberRepository.findAll(PagingUtils.from(request)),
                EntityConverter::toMappingListItem
        );
    }
}
