package com.promise.collector.monitor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.promise.collector.message.UpperStreamMessageExchanger;
import com.promise.collector.model.Device;
import com.promise.platform.devicebasic.sdk.message.Message;
import com.promise.platform.devicebasic.sdk.model.DeviceHardwareType;
import com.promise.platform.devicebasic.sdk.ws.DeviceMonitorRequestV1;
import com.promise.platform.devicebasic.sdk.ws.MonitorConfigurationV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class MonitorService {
    private static final Logger log = LoggerFactory.getLogger(MonitorService.class);
    private final List<Device> devices = new LinkedList<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(100);
    // TODO load default configuration.
    private final Map<DeviceHardwareType, MonitorConfigurationV1> configurations = new HashMap<>();

    // the futures is used to terminate the threads.
    private final Map<Long, Future<?>> futures = new HashMap<>();

    @Autowired
    private UpperStreamMessageExchanger upperStreamMessageExchanger;

    @Autowired
    private ObjectMapper objectMapper;

    public void onMonitorMessage(WebSocketSession session, Message message) {
        var monitorRequest = objectMapper.convertValue(message.getPayload(), DeviceMonitorRequestV1.class);
        var device = new Device();
        // TODO
        device.setServerMonitorPlugin(null);
        device.setDht(monitorRequest.getDht());
        device.setDiscoverRequest(monitorRequest.getDiscoverRequest());
        device.setMonitorConfiguration(configurations.get(monitorRequest.getDht()));

    }

    /**
     * Add a device to the monitor list.
     *
     * @param device the device to add.
     */
    public synchronized void addDevice(Device device) {
        if (devices.contains(device)) {
            log.warn("Add device {} skipped, device already exist.", device);
        }
        var configuration = configurations.get(device.getDht());
        device.setMonitorConfiguration(configuration);
        var monitorRunnable = new MonitorRunnable(device, upperStreamMessageExchanger);
        futures.putIfAbsent(device.getId(), executorService.submit(monitorRunnable));
        devices.add(device);
    }

    public void removeDevice(Long id) {
        var f = futures.get(id);
        if (f == null) {
            log.warn("Remove device {} skipped, device not exist.", id);
            return;
        }
        f.cancel(true);
        devices.removeIf(d -> d.getId().equals(id));
    }

    public void updateConfiguration(MonitorConfigurationV1 configuration) {

    }
}
