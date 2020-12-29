package com.promise.platform.devicebasic.sdk.dto.device;

import com.promise.platform.common.Resource;
import com.promise.platform.devicebasic.sdk.model.DeviceHardwareType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class GetDeviceListItemV1 extends Resource {
    private DeviceType type;
    private String vendor;
    private String model;
    private String subModel;
    private String sn;
    private DeviceHardwareType dht;
}
