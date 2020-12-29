package com.promise.collector.message;

import com.promise.collector.state.StatePublisher;
import com.promise.platform.devicebasic.sdk.message.Message;
import com.promise.platform.devicebasic.sdk.message.MessageInterceptorChain;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.client.WebSocketClient;

import static com.promise.collector.message.ConnectionState.*;
import static com.promise.collector.state.CollectorStateEvent.*;

@Component
public class CollectorWebSocketHandler implements WebSocketHandler, ConnectionManager {
    private final static Logger log = LoggerFactory.getLogger(CollectorWebSocketHandler.class);
    private final WebSocketClient client;
    private volatile ConnectionState connectionState;
    private volatile long openingAt;

    @Autowired
    private StatePublisher statePublisher;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SessionPublisher sessionPublisher;

    @Autowired
    private MessageInterceptorChain messageInterceptorChain;

    @Autowired
    public CollectorWebSocketHandler(WebSocketClient client) {
        this.client = client;
    }

    @Value("${remote.webSocket.endpoint}")
    private String endpoint;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        connectionState = Opened;
        sessionPublisher.publish(session);
        statePublisher.publish(WsConnected);
        log.info("WebSocket session established.");
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> msg) throws Exception {
        if (!(msg instanceof TextMessage)) {
            log.warn("Discard non text message.");
            return;
        }
        var message = objectMapper.readValue(((TextMessage) msg).getPayload(), Message.class);
        log.info("receive message {} from {}", message, session.getRemoteAddress());
        messageInterceptorChain.intercept(session, message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.warn("WebSocket session got transport error", exception);
        statePublisher.publish(WsConnectionTransportError);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        connectionState = Closed;
        log.warn("WebSocket session closed with status {}", closeStatus);
        statePublisher.publish(WsConnectionLost);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    @Override
    public void connect() {
        connectionState = Opening;
        openingAt = System.nanoTime();
        client.doHandshake(this, endpoint);
        new Thread(() -> {
            try {
                Thread.sleep(10 * 1000);
                if (connectionState == Opening) {
                    statePublisher.publish(WsConnectionTimeout);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public ConnectionState getConnectionState() {
        return connectionState;
    }

    @Override
    public long openingAt() {
        return openingAt;
    }
}