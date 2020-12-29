package com.promise.platform.devicebasic.entity;

import com.promise.platform.common.entity.MappingEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class DeviceGroupMemberEntity extends MappingEntity {
    public DeviceGroupMemberEntity(Long groupId, Long memberId) {
        super(groupId, memberId);
    }
}
