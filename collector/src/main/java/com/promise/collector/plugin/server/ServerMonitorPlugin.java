package com.promise.collector.plugin.server;

import com.promise.platform.devicebasic.sdk.dto.device.DiscoverDeviceRequestV1;
import com.promise.platform.devicemonitor.sdk.dto.ComponentType;
import com.promise.platform.devicemonitor.sdk.message.ComponentStateMessageV1;
import com.promise.platform.devicemonitor.sdk.message.DeviceStateMessageV1;

import java.util.Map;

/**
 * The interface if device monitor is required.
 */
public interface ServerMonitorPlugin {
    /**
     * Get device's state.
     * @param request the original discover request.
     * @return The device's state.
     */
    DeviceStateMessageV1 getDeviceState(DiscoverDeviceRequestV1 request);

    /**
     * Get the state of a specific component.
     * <p/>
     * Note that a type of component may have many instances.
     * @param request the original discover request.
     * @param componentType the specific component type.
     * @return the component's state map, the key is the location of the component.
     */
    Map<String, ComponentStateMessageV1> getComponentState(DiscoverDeviceRequestV1 request, ComponentType componentType);
}
