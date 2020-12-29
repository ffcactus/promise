package com.promise.platform.devicebasic.sdk.dto.devicegroup;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class CreateDeviceGroupRequestV1 {
    private String name;
    private String description;
    private DeviceGroupType type;
}
