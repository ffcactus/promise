package com.promise.platform.devicebasic.scheduler;

import com.promise.platform.devicebasic.entity.DeviceEntity;
import com.promise.platform.devicebasic.entity.EntityConverter;
import com.promise.platform.devicebasic.repository.DeviceRepository;
import com.promise.platform.devicebasic.sdk.message.Exchanger;
import com.promise.platform.devicebasic.sdk.message.GenericMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static com.promise.platform.devicebasic.sdk.message.MessageCommand.DeviceMonitor;

@Component
public class DeviceScheduler {

    private static final Logger log = LoggerFactory.getLogger(DeviceScheduler.class);

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private Exchanger exchanger;

//    @PostConstruct
//    public void postConstruct() {
//        executorService.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
//                var devices = deviceRepository.findAll();
//                for (var device : devices) {
//                    schedule(device);
//                }
//            }
//        }, 20, 20, TimeUnit.SECONDS);
//    }

    @EventListener
    public void onNewDevice(DeviceEntity device) {
        schedule(device);
    }

    /**
     * Schedule a device monitor task to a collector.
     * @param device
     */
    private void schedule(DeviceEntity device) {
        var msg = new GenericMessage<>();
        msg.setDeviceId(device.getId());
        msg.setCommand(DeviceMonitor);
        msg.setPayload(EntityConverter.toDeviceMonitorRequest(device));
        try {
            exchanger.exchange(msg);
        } catch (Exception e) {
            log.warn("Failed to schedule device {}.", device, e);
        }
    }
}
