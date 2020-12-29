package com.promise.collector.plugin;

import com.promise.platform.devicebasic.sdk.dto.device.DeviceType;
import com.promise.platform.devicebasic.sdk.dto.device.DiscoverDeviceRequestV1;
import com.promise.platform.devicebasic.sdk.dto.device.GetDeviceResponseV1;
import com.promise.platform.devicebasic.sdk.model.DeviceHardwareType;

import java.io.IOException;
import java.util.List;

/**
 * A plugin is a component that is used to managed a kind of device from a specific device vendor.
 */
public interface Plugin {

    String getVendor();

    DeviceType getDeviceType();

    /**
     * Return the {@link DeviceHardwareType} that this plugin for.
     *
     * @return The supported {@link DeviceHardwareType}
     */
    List<DeviceHardwareType> supportedDht();

    boolean support(DiscoverDeviceRequestV1 request);

    GetDeviceResponseV1 discover(DiscoverDeviceRequestV1 request) throws IOException;
}
