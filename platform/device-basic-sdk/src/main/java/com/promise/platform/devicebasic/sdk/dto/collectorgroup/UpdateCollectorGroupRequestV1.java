package com.promise.platform.devicebasic.sdk.dto.collectorgroup;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateCollectorGroupRequestV1 {
    private String name;
    private String description;
}
