package com.promise.platform.devicebasic.entity;

import com.promise.platform.common.entity.MappingEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * The mapping from device group to collector group.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class DgCgMapping extends MappingEntity {

}
