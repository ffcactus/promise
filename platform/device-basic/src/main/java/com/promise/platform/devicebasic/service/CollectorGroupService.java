package com.promise.platform.devicebasic.service;

import com.promise.platform.common.dto.*;
import com.promise.platform.common.entity.MappingEntity;
import com.promise.platform.common.util.PagingUtils;
import com.promise.platform.devicebasic.entity.CollectorGroupMemberEntity;
import com.promise.platform.devicebasic.entity.DeviceGroupEntity;
import com.promise.platform.devicebasic.entity.DgCgMapping;
import com.promise.platform.devicebasic.entity.EntityConverter;
import com.promise.platform.devicebasic.repository.*;
import com.promise.platform.devicebasic.sdk.dto.collector.GetCollectorListItemV1;
import com.promise.platform.devicebasic.sdk.dto.collectorgroup.CreateCollectorGroupRequestV1;
import com.promise.platform.devicebasic.sdk.dto.collectorgroup.GetCollectorGroupListItemV1;
import com.promise.platform.devicebasic.sdk.dto.collectorgroup.GetCollectorGroupResponseV1;
import com.promise.platform.devicebasic.sdk.dto.collectorgroup.UpdateCollectorGroupRequestV1;
import com.promise.platform.devicebasic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.stream.Collectors;

import static com.promise.platform.devicebasic.sdk.dto.devicegroup.DeviceGroupType.Collector;

@Service
public class CollectorGroupService {

    @Autowired
    private CollectorGroupMemberRepository collectorGroupMemberRepository;

    @Autowired
    private CollectorGroupRepository collectorGroupRepository;

    @Autowired
    private RecordedCollectorRepository recordedCollectorRepository;

    @Autowired
    private DeviceGroupRepository deviceGroupRepository;

    @Autowired
    private DgCgMappingRepository dgCgMappingRepository;

    /**
     * Create a collector group.
     * <p/>
     * Because a collector group always mapping to a device group, the device group and it's mapping will be created together.
     * @param request the create collector group request.
     * @return the collector group created.
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public GetCollectorGroupResponseV1 create(CreateCollectorGroupRequestV1 request) {
        var collectorGroup = EntityConverter.generate(request);
        collectorGroup = collectorGroupRepository.save(collectorGroup);
        // create device group.
        var deviceGroup = new DeviceGroupEntity();
        deviceGroup.setName(collectorGroup.getName());
        deviceGroup.setDescription(collectorGroup.getDescription());
        deviceGroup.setType(Collector);
        deviceGroup = deviceGroupRepository.save(deviceGroup);
        // create dgcg mapping.
        var dgcg = new DgCgMapping();
        dgcg.setLeftId(deviceGroup.getId());
        dgcg.setRightId(collectorGroup.getId());
        dgCgMappingRepository.save(dgcg);
        return EntityConverter.toCollectorGroupResponse(collectorGroup);
    }

    public GetCollectorGroupResponseV1 getById(Long id) {
        return EntityConverter.toCollectorGroupResponse(collectorGroupRepository.findById(id).orElseThrow());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public GetCollectorGroupResponseV1 deleteById(Long id) {
        var entity = collectorGroupRepository.findById(id).orElseThrow();
        collectorGroupRepository.deleteById(id);
        return EntityConverter.toCollectorGroupResponse(entity);
    }

    public GetCollectorGroupResponseV1 updateById(Long id, UpdateCollectorGroupRequestV1 request) {
        return null;
    }

    public ListResponseV1<GetCollectorGroupListItemV1> getAll(ListRequestV1 request) {
        return PagingUtils.from(
                collectorGroupRepository.findAll(PagingUtils.from(request)),
                EntityConverter::toCollectorGroupListItem
        );
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public ListResponseV1<GetCollectorListItemV1> getMembers(Long id, ListRequestV1 request) {
        var mappings = collectorGroupMemberRepository.findByLeftId(id, PagingUtils.from(request));
        var entities = recordedCollectorRepository.findAllById(mappings.stream().map(MappingEntity::getRightId).collect(Collectors.toList()));
        var resp = new ListResponseV1<GetCollectorListItemV1>();
        resp.setPage(request.getPage());
        resp.setSize(request.getSize());
        resp.setTotal(mappings.size());
        resp.setSortDirection(request.getSortDirection());
        resp.setSortBy(request.getSortBy());
        resp.setItems(Streamable.of(entities).stream().map(
                EntityConverter::toCollectorListItem
        ).collect(Collectors.toList()));
        return resp;
    }

    @Transactional
    public GroupMemberUpdateResponseV1 updateMember(Long id, GroupMemberUpdateRequestV1 request) {
        var all = collectorGroupMemberRepository.findByLeftId(id);
        var allMembers = all.stream().map(MappingEntity::getRightId).collect(Collectors.toList());
        var results = new HashMap<Long, GroupMemberUpdateResult>();
        request.getUpdates().forEach((memberId, operation) -> {
            switch (operation) {
                case Add:
                    if (allMembers.contains(memberId)) {
                        results.put(memberId, GroupMemberUpdateResult.AlreadyExist);
                    } else {
                        collectorGroupMemberRepository.save(new CollectorGroupMemberEntity(id, memberId));
                        results.put(memberId, GroupMemberUpdateResult.Success);
                    }
                    break;
                case Remove:
                    if (!allMembers.contains(memberId)) {
                        results.put(memberId, GroupMemberUpdateResult.NoSuchElement);
                    } else {
                        collectorGroupMemberRepository.deleteByRightId(memberId);
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
}
