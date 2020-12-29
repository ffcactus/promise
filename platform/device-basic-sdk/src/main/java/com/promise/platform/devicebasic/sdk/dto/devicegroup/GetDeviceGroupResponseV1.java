package com.promise.platform.devicebasic.sdk.dto.devicegroup;

import com.promise.platform.common.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GetDeviceGroupResponseV1 extends Resource {
    private DeviceGroupType type;
}
