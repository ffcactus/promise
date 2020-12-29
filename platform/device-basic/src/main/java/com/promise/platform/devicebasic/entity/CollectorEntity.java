package com.promise.platform.devicebasic.entity;

import com.promise.platform.common.URIs;
import com.promise.platform.common.entity.ResourceEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class CollectorEntity extends ResourceEntity {
    private String ip;
    private String sn;
    private String version;
    private boolean connected;
    private LocalDateTime lastConnectedAt;
    private LocalDateTime lastLostAt;
    public String getUri() {
        return URIs.CollectorBaseUri + "/" + super.getId();
    }
}
