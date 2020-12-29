package com.promise.platform.common.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * A common entity that represents a mapping.
 *
 */
@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class MappingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private Long leftId;
    private Long rightId;

    public MappingEntity(Long leftId, Long rightId) {
        this.leftId = leftId;
        this.rightId = rightId;
    }
}
