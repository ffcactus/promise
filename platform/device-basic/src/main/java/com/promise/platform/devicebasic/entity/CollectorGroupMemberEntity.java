package com.promise.platform.devicebasic.entity;

import com.promise.platform.common.entity.MappingEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Represents a mapping from a collector and a collector-group.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class CollectorGroupMemberEntity extends MappingEntity {
    public CollectorGroupMemberEntity(Long groupId, Long memberId) {
        super(groupId, memberId);
    }
}
