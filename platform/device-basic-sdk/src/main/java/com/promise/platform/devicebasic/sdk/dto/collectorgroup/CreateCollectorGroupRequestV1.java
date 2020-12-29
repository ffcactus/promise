package com.promise.platform.devicebasic.sdk.dto.collectorgroup;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CreateCollectorGroupRequestV1 {
    private String name;
    private String description;
}
