package com.promise.collector.plugin.server.huawei;

import com.promise.collector.httpclient.HttpClientUtils;
import com.promise.collector.plugin.server.ServerMonitorPlugin;
import com.promise.collector.plugin.server.huawei.dto.HuaweiRedfishProcessor;
import com.promise.collector.plugin.server.redfish.RedfishUtil;
import com.promise.collector.plugin.server.redfish.dto.*;
import com.promise.platform.devicebasic.sdk.dto.device.DiscoverDeviceRequestV1;
import com.promise.platform.devicemonitor.sdk.dto.ComponentType;
import com.promise.platform.devicemonitor.sdk.dto.DeviceConnectionState;
import com.promise.platform.devicemonitor.sdk.dto.DevicePowerState;
import com.promise.platform.devicemonitor.sdk.dto.HealthState;
import com.promise.platform.devicemonitor.sdk.message.ComponentStateMessageV1;
import com.promise.platform.devicemonitor.sdk.message.DeviceStateMessageV1;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component("HuaweiV5ServerMonitorPlugin")
public class HuaweiV5ServerMonitorPlugin implements ServerMonitorPlugin {

    private static final Logger log = LoggerFactory.getLogger(HuaweiV5ServerMonitorPlugin.class);

    @Autowired
    @Qualifier("acceptSelfSignedCertificateClient")
    private CloseableHttpClient client;

    public DeviceStateMessageV1 getDeviceState(DiscoverDeviceRequestV1 request) {
        var message = new DeviceStateMessageV1();
        try {
            var httpResp = client.execute(RedfishUtil.getRequest(request, "/redfish/v1/Systems/1"));
            var redfishSystem = HttpClientUtils.getObjectFromResponse(httpResp, RedfishSystem.class);
            message.setConnection(DeviceConnectionState.Online);
            message.setPower(RedfishUtil.getPowerState(redfishSystem.getPowerState()));
            message.setHealth(RedfishUtil.getHealthState(redfishSystem.getStatus().getHealth()));
        } catch (Exception e) {
            log.warn("Get device state failed", e);
            message.setConnection(DeviceConnectionState.Offline);
            message.setPower(DevicePowerState.Unknown);
            message.setHealth(HealthState.Unknown);
        }
        return message;
    }

    public Map<String, ComponentStateMessageV1> getComponentState(DiscoverDeviceRequestV1 request, ComponentType componentType) {
        switch (componentType) {
            case Processor:
                return getProcessorState(request);
            case Memory:
                return getMemoryState(request);
            case Drive:
                return getDriveState(request);
            case PowerSupply:
                return getPowerSupplyState(request);
            case Fan:
                return getFanState(request);
            case Card:
                return getCardState(request);
            default:
                log.warn("Get component state failed, unknown component type {}", componentType);
                return null;
        }
    }

    private Map<String, ComponentStateMessageV1> getProcessorState(DiscoverDeviceRequestV1 request) {
        var ret = new HashMap<String, ComponentStateMessageV1>();
        try {
            var httpResp = client.execute(RedfishUtil.getRequest(request, "/redfish/v1/Systems/1/Processors"));
            var collectionResp = HttpClientUtils.getObjectFromResponse(httpResp, RedfishCollectionResponse.class);
            var uris = collectionResp.getMembers().stream().map(OdataId::getId).collect(Collectors.toList());
            for (var uri : uris) {
                var processor = HttpClientUtils.getObjectFromResponse(
                        client.execute(RedfishUtil.getRequest(request, uri)),
                        HuaweiRedfishProcessor.class
                );
                var state = new ComponentStateMessageV1();
                state.setType(ComponentType.Processor);
                state.setLocation(processor.getLocation());
                state.setHealth(RedfishUtil.getHealthState(processor.getStatus().getHealth()));
                state.setInstall(RedfishUtil.getInstallState(processor.getStatus().getState()));
                ret.put(processor.getLocation(), state);
            }
            log.info("Discover processors state {} from request {}.", ret, request);
            return ret;
        } catch (Exception e) {
            log.info("Discover processors state from request {} failed.", request, e);
            return null;
        }
    }

    private Map<String, ComponentStateMessageV1> getMemoryState(DiscoverDeviceRequestV1 request) {
        var ret = new HashMap<String, ComponentStateMessageV1>();
        try {
            var httpResp = client.execute(RedfishUtil.getRequest(request, "/redfish/v1/Systems/1/Memory"));
            var collectionResp = HttpClientUtils.getObjectFromResponse(httpResp, RedfishCollectionResponse.class);
            var uris = collectionResp.getMembers().stream().map(OdataId::getId).collect(Collectors.toList());
            for (var uri : uris) {
                var processor = HttpClientUtils.getObjectFromResponse(
                        client.execute(RedfishUtil.getRequest(request, uri)),
                        RedfishMemory.class
                );
                var state = new ComponentStateMessageV1();
                state.setType(ComponentType.Memory);
                state.setLocation(processor.getLocation());
                state.setHealth(RedfishUtil.getHealthState(processor.getStatus().getHealth()));
                state.setInstall(RedfishUtil.getInstallState(processor.getStatus().getState()));
                ret.put(processor.getLocation(), state);
            }
            log.info("Discover memory state {} from request {}.", ret, request);
            return ret;
        } catch (Exception e) {
            log.info("Discover memory state from request {} failed.", request, e);
            return null;
        }
    }

    private Map<String, ComponentStateMessageV1> getDriveState(DiscoverDeviceRequestV1 request) {
        // TODO handling paging here.
        var ret = new HashMap<String, ComponentStateMessageV1>();
        try {
            var httpResp = client.execute(RedfishUtil.getRequest(request, "/redfish/v1/Chassis/1/Drives"));
            var collectionResp = HttpClientUtils.getObjectFromResponse(httpResp, RedfishCollectionResponse.class);
            var uris = collectionResp.getMembers().stream().map(OdataId::getId).collect(Collectors.toList());
            for (var uri : uris) {
                var processor = HttpClientUtils.getObjectFromResponse(
                        client.execute(RedfishUtil.getRequest(request, uri)),
                        RedfishDrive.class
                );
                var state = new ComponentStateMessageV1();
                state.setType(ComponentType.Drive);
                state.setLocation(processor.getLocation());
                state.setHealth(RedfishUtil.getHealthState(processor.getStatus().getHealth()));
                state.setInstall(RedfishUtil.getInstallState(processor.getStatus().getState()));
                ret.put(processor.getLocation(), state);
            }
            log.info("Discover drive state {} from request {}.", ret, request);
            return ret;
        } catch (Exception e) {
            log.info("Discover drive state from request {} failed.", request, e);
            return null;
        }
    }

    private Map<String, ComponentStateMessageV1> getPowerSupplyState(DiscoverDeviceRequestV1 request) {
        var ret = new HashMap<String, ComponentStateMessageV1>();
        try {
            var httpResp = client.execute(RedfishUtil.getRequest(request, "/redfish/v1/Chassis/1/Power"));
            var power = HttpClientUtils.getObjectFromResponse(httpResp, RedfishPower.class);
            for (var powerSupply : power.getPowerSupplies()) {
                var state = new ComponentStateMessageV1();
                state.setType(ComponentType.PowerSupply);
                state.setLocation(powerSupply.getLocation());
                state.setHealth(RedfishUtil.getHealthState(powerSupply.getStatus().getHealth()));
                state.setInstall(RedfishUtil.getInstallState(powerSupply.getStatus().getState()));
                ret.put(powerSupply.getLocation(), state);
            }
            log.info("Discover power supply state {} from request {}.", ret, request);
            return ret;
        } catch (Exception e) {
            log.info("Discover power supply state from request {} failed.", request, e);
            return null;
        }
    }

    private Map<String, ComponentStateMessageV1> getFanState(DiscoverDeviceRequestV1 request) {
        var ret = new HashMap<String, ComponentStateMessageV1>();
        try {
            var httpResp = client.execute(RedfishUtil.getRequest(request, "/redfish/v1/Chassis/1/Thermal"));
            var thermal = HttpClientUtils.getObjectFromResponse(httpResp, RedfishThermal.class);
            for (var fan : thermal.getFans()) {
                var state = new ComponentStateMessageV1();
                state.setType(ComponentType.Fan);
                state.setLocation(fan.getLocation());
                state.setHealth(RedfishUtil.getHealthState(fan.getStatus().getHealth()));
                state.setInstall(RedfishUtil.getInstallState(fan.getStatus().getState()));
                ret.put(fan.getLocation(), state);
            }
            log.info("Discover fan state {} from request {}.", ret, request);
            return ret;
        } catch (Exception e) {
            log.info("Discover fan state from request {} failed.", request, e);
            return null;
        }
    }

    private Map<String, ComponentStateMessageV1> getCardState(DiscoverDeviceRequestV1 request) {
        // TODO
        return null;
    }
}
