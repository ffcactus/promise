package com.promise.collector.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.promise.platform.devicebasic.sdk.message.Exchanger;
import com.promise.platform.devicebasic.sdk.message.GenericMessage;
import com.promise.platform.devicebasic.sdk.message.Message;
import com.promise.platform.devicebasic.sdk.message.MessageInterceptorChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * The exchanger for messaging with upper stream.
 */

@Component
public class UpperStreamMessageExchanger implements Exchanger {
    private WebSocketSession session;
    private static final Logger log = LoggerFactory.getLogger(UpperStreamMessageExchanger.class);
    // TODO remove remaining futures if the response is not received within limit.
    private final Map<String, CompletableFuture<Message>> futures = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MessageInterceptorChain messageInterceptorChain;

    @PostConstruct
    public void postConstruct() {
        messageInterceptorChain.register(this);
    }

    @Override
    public Message exchange(GenericMessage<?> msg, long timeout, TimeUnit unit) throws Exception {
        var future = new CompletableFuture<Message>();
        futures.putIfAbsent(msg.getId(), future);
        log.info("Sending message {} to {}.", msg, session.getRemoteAddress());
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(msg)));
        return future.get(timeout, unit);
    }

    @Override
    public Message exchange(GenericMessage<?> msg) throws Exception {
        return exchange(msg, 3, TimeUnit.SECONDS);
    }

    @EventListener
    public void onNewSession(WebSocketSession session) {
        this.session = session;
        log.info("Session is received.");

    }

    @Override
    public Message intercept(WebSocketSession session, Message message) {
        var f = futures.remove(message.getId());
        if (f != null) {
            f.complete(message);
            return null;
        }
        return message;
    }

    @Override
    public int order() {
        return 0;
    }
}
