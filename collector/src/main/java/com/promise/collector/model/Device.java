package com.promise.collector.model;

import com.promise.collector.plugin.server.ServerMonitorPlugin;
import com.promise.platform.devicebasic.sdk.dto.device.DiscoverDeviceRequestV1;
import com.promise.platform.devicebasic.sdk.model.DeviceHardwareType;
import com.promise.platform.devicebasic.sdk.ws.MonitorConfigurationV1;

import java.util.Objects;

/**
 * The device object contains enough information for collector to continue operate on this device.
 */
public class Device {
    private Long id;
    private DiscoverDeviceRequestV1 discoverRequest;
    private DeviceHardwareType dht;
    private MonitorConfigurationV1 monitorConfiguration;
    private ServerMonitorPlugin serverMonitorPlugin;

    public Device() {

    }

    public synchronized DiscoverDeviceRequestV1 getDiscoverRequest() {
        return discoverRequest;
    }

    public synchronized void setDiscoverRequest(DiscoverDeviceRequestV1 discoverRequest) {
        this.discoverRequest = discoverRequest;
    }

    public synchronized MonitorConfigurationV1 getMonitorConfiguration() {
        return monitorConfiguration;
    }

    public synchronized void setMonitorConfiguration(MonitorConfigurationV1 monitorConfiguration) {
        this.monitorConfiguration = monitorConfiguration;
    }

    public DeviceHardwareType getDht() {
        return dht;
    }

    public void setDht(DeviceHardwareType dht) {
        this.dht = dht;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServerMonitorPlugin getServerMonitorPlugin() {
        return serverMonitorPlugin;
    }

    public void setServerMonitorPlugin(ServerMonitorPlugin serverMonitorPlugin) {
        this.serverMonitorPlugin = serverMonitorPlugin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return id.equals(device.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Device{" +
                "discoverRequest=" + discoverRequest +
                '}';
    }

}
