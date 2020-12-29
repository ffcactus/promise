package com.promise.platform.devicebasic.sdk.dto.collectorgroup;

import com.promise.platform.common.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GetCollectorGroupResponseV1 extends Resource {
    private int elementCount;
}
