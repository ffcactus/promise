package com.promise.platform.devicebasic.message;

import com.promise.platform.devicebasic.repository.SessionRepository;
import com.promise.platform.devicebasic.sdk.message.Message;
import com.promise.platform.devicebasic.sdk.message.MessageInterceptorChain;
import com.promise.platform.devicebasic.service.CollectorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.Objects;

/**
 * The handler that is used to receive and send message from and to collector.
 */
@Component("CollectorWebSocketHandler")
public class CollectorWebSocketHandler implements WebSocketHandler {

    private static final Logger log = LoggerFactory.getLogger(CollectorWebSocketHandler.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageInterceptorChain messageInterceptorChain;

    @Autowired
    private CollectorService collectorService;

    @Autowired
    private SessionRepository sessionRepository;



    /**
     * Use this callback to save the session so we can use it to send message to the collector at any time.
     *
     * @param session The incoming session.
     * @throws Exception In case of any exception happened.
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Web socket session {} is established.", Objects.requireNonNull(session.getRemoteAddress()).toString());
    }

    /**
     * Handle the coming message.
     * If the message is the right type, send to message processor.
     *
     * @param session The session the message from.
     * @param message The message object.
     * @throws Exception In case of any exception happened.
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if (message instanceof PingMessage) {
            session.sendMessage(new PongMessage());
            return;
        }

        if (!(message instanceof TextMessage)) {
            log.warn("Unknown message type from {}", session.getRemoteAddress());
            return;
        }

        log.info("handle message from {}", session.getRemoteAddress());
        messageInterceptorChain.intercept(
                session,
                objectMapper.readValue(((TextMessage) message).getPayload(), Message.class)
        );
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.info("Connection from {} got transport error.", session.getRemoteAddress(), exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        log.info("Web socket session {} is closed with status {}.", session.getRemoteAddress(), status);
        collectorService.handleConnectionLost(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

}
