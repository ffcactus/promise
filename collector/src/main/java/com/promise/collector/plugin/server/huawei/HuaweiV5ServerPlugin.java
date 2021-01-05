package com.promise.collector.plugin.server.huawei;

import com.promise.collector.httpclient.HttpClientUtils;
import com.promise.collector.plugin.PluginManager;
import com.promise.collector.plugin.server.ServerMonitorPlugin;
import com.promise.collector.plugin.server.ServerPlugin;
import com.promise.collector.plugin.server.huawei.dto.HuaweiRedfishProcessor;
import com.promise.collector.plugin.server.redfish.RedfishUtil;
import com.promise.collector.plugin.server.redfish.dto.*;
import com.promise.platform.devicebasic.sdk.dto.device.*;
import com.promise.platform.devicebasic.sdk.model.DeviceHardwareType;
import com.promise.platform.devicemonitor.sdk.dto.ComponentType;
import com.promise.platform.devicemonitor.sdk.message.ComponentStateMessageV1;
import com.promise.platform.devicemonitor.sdk.message.DeviceStateMessageV1;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class HuaweiV5ServerPlugin implements ServerPlugin, ServerMonitorPlugin {

    private static final Logger log = LoggerFactory.getLogger(HuaweiV5ServerPlugin.class);

    @Qualifier("HuaweiV5ServerMonitorPlugin")
    @Autowired
    private ServerMonitorPlugin monitorPlugin;

    @Autowired
    @Qualifier("acceptSelfSignedCertificateClient")
    private CloseableHttpClient client;

    @Autowired
    private PluginManager pluginManager;

    @PostConstruct
    public void postConstruct() {
        pluginManager.register(this);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getVendor() {
        return "Huawei";
    }

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.Server;
    }

    @Override
    public List<DeviceHardwareType> supportedDht() {
        return List.of(
                DeviceHardwareType.Huawei_1288H_V5,
                DeviceHardwareType.Huawei_2288H_V5
        );
    }

    @Override
    public boolean support(DiscoverDeviceRequestV1 request) {
        try {
            var httpResp = client.execute(RedfishUtil.getRequest(request, "/redfish/v1/Chassis/1"));
            var restResp = HttpClientUtils.getObjectFromResponse(httpResp, RedfishChassis.class);
            return support(restResp);
        } catch (IOException e) {
            log.debug("check support failed.", e);
        }
        return false;
    }

    private boolean support(RedfishChassis chassis) {
        if (chassis == null) {
            return false;
        }
        var chassisType = chassis.getChassisType();
        var manufacturer = chassis.getManufacturer();
        var model = chassis.getModel();
        if (chassisType == null || !chassisType.equalsIgnoreCase("Rack")) {
            return false;
        }
        if (manufacturer == null || !manufacturer.equalsIgnoreCase("Huawei")) {
            return false;
        }
        if (model == null || !chassis.getModel().contains("V5")) {
            return false;
        }
        return true;
    }

    @Override
    public GetDeviceResponseV1 discover(DiscoverDeviceRequestV1 request) throws IOException {
        var httpResp = client.execute(RedfishUtil.getRequest(request, "/redfish/v1/Chassis/1"));
        var chassis = HttpClientUtils.getObjectFromResponse(httpResp, RedfishChassis.class);
        var discoverResp = new GetDeviceResponseV1();
        discoverResp.setName(request.getName());
        discoverResp.setDescription(request.getDescription());
        discoverResp.setVendor(chassis.getManufacturer());
        discoverResp.setModel(chassis.getModel());
        discoverResp.setSn(chassis.getSerialNumber());
        discoverResp.setSubModel("");
        discoverResp.setType(DeviceType.Server);
        discoverResp.setDht(getDht(chassis.getModel()));
        discoverResp.setProcessors(discoverProcessors(request));
        discoverResp.setMemory(discoverMemory(request));
        discoverResp.setDrives(discoverDrives(request, chassis));
        discoverResp.setPowerSupplies(discoverPowerSupplies(request));
        discoverResp.setFans(discoverFans(request));
        return discoverResp;
    }

    private List<ProcessorV1> discoverProcessors(DiscoverDeviceRequestV1 request) throws IOException {
        var httpResp = client.execute(RedfishUtil.getRequest(request, "/redfish/v1/Systems/1/Processors"));
        var collectionResp = HttpClientUtils.getObjectFromResponse(httpResp, RedfishCollectionResponse.class);
        var uris = collectionResp.getMembers().stream().map(OdataId::getId).collect(Collectors.toList());
        var processors = new ArrayList<ProcessorV1>();
        for (var uri : uris) {
            processors.add(HttpClientUtils.getObjectFromResponse(
                    client.execute(RedfishUtil.getRequest(request, uri)),
                    HuaweiRedfishProcessor.class
            ).toResponse());
        }
        log.info("Discover {} processors from request {}", processors.size(), request);
        return processors;
    }

    private List<MemoryV1> discoverMemory(DiscoverDeviceRequestV1 request) throws IOException {
        var httpResp = client.execute(RedfishUtil.getRequest(request, "/redfish/v1/Systems/1/Memory"));
        var collectionResp = HttpClientUtils.getObjectFromResponse(httpResp, RedfishCollectionResponse.class);
        var uris = collectionResp.getMembers().stream().map(OdataId::getId).collect(Collectors.toList());
        var memory = new ArrayList<MemoryV1>();
        for (var uri : uris) {
            memory.add(HttpClientUtils.getObjectFromResponse(
                    client.execute(RedfishUtil.getRequest(request, uri)),
                    RedfishMemory.class
            ).toResponse());
        }
        log.info("Discover {} memory from request {}", memory.size(), request);
        return memory;
    }

    private List<DriveV1> discoverDrives(DiscoverDeviceRequestV1 request, RedfishChassis chassis) throws IOException {
        var uris = chassis.getLinks().get("Drives").stream().map(OdataId::getId).collect(Collectors.toList());
        var drives = new ArrayList<DriveV1>();
        for (var uri : uris) {
            drives.add(HttpClientUtils.getObjectFromResponse(
                    client.execute(RedfishUtil.getRequest(request, uri)),
                    RedfishDrive.class
            ).toResponse());
        }
        log.info("Discover {} drives from request {}", drives.size(), request);
        return drives;
    }

    private List<PowerSupplyV1> discoverPowerSupplies(DiscoverDeviceRequestV1 request) throws IOException {
        var httpResp = client.execute(RedfishUtil.getRequest(request, "/redfish/v1/Chassis/1/Power"));
        var power = HttpClientUtils.getObjectFromResponse(httpResp, RedfishPower.class);
        var powerSupplies = new ArrayList<PowerSupplyV1>();
        for (var powerSupply : power.getPowerSupplies()) {
            powerSupplies.add(powerSupply.toResponse());
        }
        log.info("Discover {} power supplies from request {}", powerSupplies.size(), request);
        return powerSupplies;
    }

    private List<FanV1> discoverFans(DiscoverDeviceRequestV1 request) throws IOException {
        var httpResp = client.execute(RedfishUtil.getRequest(request, "/redfish/v1/Chassis/1/Thermal"));
        var thermal = HttpClientUtils.getObjectFromResponse(httpResp, RedfishThermal.class);
        var fans = new ArrayList<FanV1>();
        for (var fan : thermal.getFans()) {
            fans.add(fan.toResponse());
        }
        log.info("Discover {} fans from request {}", fans.size(), request);
        return fans;
    }

    private DeviceHardwareType getDht(String dht) {
        if (dht.equalsIgnoreCase("2288H V5")) {
            return DeviceHardwareType.Huawei_2288H_V5;
        }
        if (dht.equalsIgnoreCase("1288H V5")) {
            return DeviceHardwareType.Huawei_1288H_V5;
        }
        return DeviceHardwareType.Huawei_V5;
    }

    @Override
    public DeviceStateMessageV1 getDeviceState(DiscoverDeviceRequestV1 request) {
        return monitorPlugin.getDeviceState(request);
    }

    @Override
    public Map<String, ComponentStateMessageV1> getComponentState(DiscoverDeviceRequestV1 request, ComponentType componentType) {
        return monitorPlugin.getComponentState(request, componentType);
    }
}
