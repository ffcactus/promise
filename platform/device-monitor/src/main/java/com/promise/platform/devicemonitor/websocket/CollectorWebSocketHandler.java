package com.promise.platform.devicemonitor.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.Objects;

/**
 * The WebSocket handler between collector and device monitor.
 */
@Component("CollectorWebSocketHandler")
public class CollectorWebSocketHandler implements WebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(CollectorWebSocketHandler.class);


    @Autowired
    private MonitorMessageProcessor monitorMessageProcessor;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Web socket session {} is established.", Objects.requireNonNull(session.getRemoteAddress()).toString());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if (message instanceof PingMessage) {
            session.sendMessage(new PongMessage());
            return;
        }
        monitorMessageProcessor.onMessage(session, message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("Web socket session {} got transport error.", Objects.requireNonNull(session.getRemoteAddress()).toString());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.info("Web socket session {} is closed.", Objects.requireNonNull(session.getRemoteAddress()).toString());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
