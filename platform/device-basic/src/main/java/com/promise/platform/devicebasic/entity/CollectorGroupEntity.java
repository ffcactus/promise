package com.promise.platform.devicebasic.entity;

import com.promise.platform.common.URIs;
import com.promise.platform.common.entity.ResourceEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class CollectorGroupEntity extends ResourceEntity {
    public String getUri() {
        return URIs.CollectorGroupBaseUri + "/" + super.getId();
    }
}
