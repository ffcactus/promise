package com.promise.platform.devicemonitor.websocket;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.promise.platform.devicemonitor.entity.ComponentStateRecord;
import com.promise.platform.devicemonitor.entity.DeviceStateRecord;
import com.promise.platform.devicemonitor.repository.ComponentStateRecordRepository;
import com.promise.platform.devicemonitor.repository.DeviceStateRecordRepository;
import com.promise.platform.devicemonitor.sdk.message.ComponentStateMessageV1;
import com.promise.platform.devicemonitor.sdk.message.DeviceMonitorMessageV1;
import com.promise.platform.devicemonitor.sdk.message.DeviceStateMessageV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class MonitorMessageProcessor {

    private static final Logger log = LoggerFactory.getLogger(MonitorMessageProcessor.class);

    @Autowired
    private DeviceStateRecordRepository deviceStateRecordRepository;

    @Autowired
    private ComponentStateRecordRepository componentStateRecordRepository;

    @Autowired
    private ObjectMapper objectMapper;

    void onMessage(WebSocketSession session, WebSocketMessage<?> message) {
        if (!(message instanceof TextMessage)) {
            log.warn("Unknown message type from {}", session.getRemoteAddress());
            return;
        }
        try {
            var monitorMessage = objectMapper.convertValue(message.getPayload(), DeviceMonitorMessageV1.class);
            var payload = monitorMessage.getPayload();
            var deviceId = monitorMessage.getId();
            switch (monitorMessage.getType()) {
                case DeviceStateMessage:
                    onDeviceStateMessage(payload, deviceId);
                    break;
                case ComponentStateMessage:
                    onComponentStateMessage(payload, deviceId);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            log.warn("process device monitor message failed", e);
        }
    }

    private void onDeviceStateMessage(JsonNode json, Long deviceId) {
        var message = objectMapper.convertValue(json, DeviceStateMessageV1.class);
        log.info("handle message {}", message);
        deviceStateRecordRepository.save(new DeviceStateRecord(
                deviceId,
                message.getConnection(),
                message.getPower(),
                message.getHealth()
        ));
    }

    private void onComponentStateMessage(JsonNode json, Long deviceId) {
        var message = objectMapper.convertValue(json, ComponentStateMessageV1.class);
        log.info("handle message {}", message);
        componentStateRecordRepository.save(new ComponentStateRecord(
                deviceId,
                message.getType(),
                message.getLocation(),
                message.getInstall(),
                message.getHealth()
        ));
    }
}
