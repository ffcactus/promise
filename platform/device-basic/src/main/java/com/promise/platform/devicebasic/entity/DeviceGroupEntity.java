package com.promise.platform.devicebasic.entity;

import com.promise.platform.common.URIs;
import com.promise.platform.common.entity.ResourceEntity;
import com.promise.platform.devicebasic.sdk.dto.devicegroup.DeviceGroupType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class DeviceGroupEntity extends ResourceEntity {
    @Enumerated(EnumType.STRING)
    private DeviceGroupType type;

    public String getUri() {
        return URIs.DeviceGroupBaseUri + "/" + super.getId();
    }
}
