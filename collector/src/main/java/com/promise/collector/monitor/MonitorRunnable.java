package com.promise.collector.monitor;

import com.promise.collector.message.UpperStreamMessageExchanger;
import com.promise.collector.model.Device;
import com.promise.platform.devicebasic.sdk.message.GenericMessage;
import com.promise.platform.devicebasic.sdk.message.MessageCommand;
import com.promise.platform.devicemonitor.sdk.dto.ComponentType;
import com.promise.platform.devicemonitor.sdk.message.ComponentStateMessageV1;
import com.promise.platform.devicemonitor.sdk.message.DeviceStateMessageV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * A {@link Runnable} that is used for device monitoring.
 */
public class MonitorRunnable implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(MonitorRunnable.class);

    private final Device device;
    private final UpperStreamMessageExchanger exchanger;

    private DeviceStateMessageV1 deviceStateMessage;
    private final Long deviceStateStart;
    private Map<ComponentType, Long> componentStateStart;
    // The key is a string of format "componentType + "_" + location.
    private Map<String, ComponentStateMessageV1> componentStateMessage;

    /**
     * Create a {@link MonitorRunnable} from a {@link Device}.
     *
     * @param device the device to monitor.
     */
    public MonitorRunnable(Device device, UpperStreamMessageExchanger exchanger) {
        this.device = device;
        this.exchanger = exchanger;
        this.deviceStateStart = System.currentTimeMillis();
        device.getMonitorConfiguration().getComponentState().forEach((componentType, v) -> {
            componentStateStart.put(componentType, System.currentTimeMillis());
        });
    }

    @Override
    public void run() {
        while (true) {
            checkDeviceStateMessage();
            checkComponentStateMessage();
            if (Thread.interrupted()) {
                log.warn("Device {} monitor thread interrupted.", device.getId());
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.warn("Device {} monitor thread interrupted in sleep", device.getId());
                return;
            }
        }
    }

    private void checkDeviceStateMessage() {
        var deviceMonitorConfig = device.getMonitorConfiguration().getDeviceState();
        if (deviceStateStart > deviceMonitorConfig.getInterval() * 1000) {
            var current = device.getServerMonitorPlugin().getDeviceState(device.getDiscoverRequest());
            if (!current.equals(deviceStateMessage)) {
                var message = new GenericMessage<DeviceStateMessageV1>();
                message.setDeviceId(device.getId());
                message.setCommand(MessageCommand.DeviceMonitor);
                message.setPayload(current);
                try {
                    exchanger.exchange(message);
                } catch (Exception e) {
                    log.warn("Sending device {} at state message {} failed.", device.getId(), message, e);
                }
                deviceStateMessage = current;
            }
        }
    }

    private void checkComponentStateMessage() {
        var componentConfig = device.getMonitorConfiguration().getComponentState();
        // For each component.
        componentConfig.forEach((componentType, config) -> {
            if (System.currentTimeMillis() - componentStateStart.get(componentType) > config.getInterval() * 1000) {
                // Get all of the instances.
                var currentInstances = device.getServerMonitorPlugin().getComponentState(
                        device.getDiscoverRequest(),
                        componentType
                );
                // If the state of the instance is different, send message.
                currentInstances.forEach((location, componentState) -> {
                    var old = componentStateMessage.get(location);
                    if (!componentState.equals(old)) {
                        var message = new GenericMessage<ComponentStateMessageV1>();
                        message.setDeviceId(device.getId());
                        message.setCommand(MessageCommand.DeviceMonitor);
                        message.setPayload(componentState);
                        try {
                            exchanger.exchange(message);
                        } catch (Exception e) {
                            log.warn("Sending device {} component {} at {} state message {} failed.", device.getId(), componentType, location, message, e);
                        }
                        // replace the old value.
                        componentStateMessage.put(location, componentState);
                    }
                });
            }
        });
    }
}
