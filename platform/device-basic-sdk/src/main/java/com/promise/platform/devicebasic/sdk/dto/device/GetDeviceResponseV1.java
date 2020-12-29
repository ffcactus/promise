package com.promise.platform.devicebasic.sdk.dto.device;

import com.promise.platform.common.Resource;
import com.promise.platform.devicebasic.sdk.model.DeviceHardwareType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class GetDeviceResponseV1 extends Resource {
    private DeviceType type;
    private String vendor;
    private String model;
    private String subModel;
    private String sn;
    private DeviceHardwareType dht;
    private List<ProcessorV1> processors;
    private List<MemoryV1> memory;
    private List<DriveV1> drives;
    private List<PowerSupplyV1> powerSupplies;
    private List<FanV1> fans;
    private List<CardV1> cards;
}
