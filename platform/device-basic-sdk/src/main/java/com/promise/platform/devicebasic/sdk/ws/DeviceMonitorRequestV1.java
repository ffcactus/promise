package com.promise.platform.devicebasic.sdk.ws;

import com.promise.platform.devicebasic.sdk.dto.device.DeviceType;
import com.promise.platform.devicebasic.sdk.dto.device.DiscoverDeviceRequestV1;
import com.promise.platform.devicebasic.sdk.model.DeviceHardwareType;
import com.promise.platform.devicebasic.sdk.message.MessageCommand;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The payload of message when the message's type is {@link MessageCommand#DeviceMonitor}
 */
@Data
@NoArgsConstructor
public class DeviceMonitorRequestV1 {
    private DiscoverDeviceRequestV1 discoverRequest;
    private DeviceType type;
    private String vendor;
    private String model;
    private String subModel;
    private String sn;
    private DeviceHardwareType dht;
}
