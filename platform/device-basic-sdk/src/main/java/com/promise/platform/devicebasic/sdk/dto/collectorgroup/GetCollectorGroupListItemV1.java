package com.promise.platform.devicebasic.sdk.dto.collectorgroup;

import com.promise.platform.common.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GetCollectorGroupListItemV1 extends Resource {
    private int elementCount;
}
