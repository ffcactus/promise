package com.promise.platform.devicebasic.sdk.dto.collector;

import com.promise.platform.common.Resource;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class GetCollectorResponseV1 extends Resource {
    private String ip;
    private String sn;
    private String version;
    private boolean connected;
    private LocalDateTime lastConnectedAt;
    private LocalDateTime lastLostAt;
}
