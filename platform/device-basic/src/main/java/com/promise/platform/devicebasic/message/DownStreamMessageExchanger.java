package com.promise.platform.devicebasic.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.promise.platform.devicebasic.message.session.SessionSelector;
import com.promise.platform.devicebasic.sdk.message.Exchanger;
import com.promise.platform.devicebasic.sdk.message.GenericMessage;
import com.promise.platform.devicebasic.sdk.message.Message;
import com.promise.platform.devicebasic.sdk.message.MessageInterceptorChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * The exchanger for sending and receiving message to and from collector.
 */
@Component
public class DownStreamMessageExchanger implements Exchanger {
    private static final Logger log = LoggerFactory.getLogger(DownStreamMessageExchanger.class);
    // TODO remove remaining futures if the response is not received within limit.
    private final Map<String, CompletableFuture<Message>> futures = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Qualifier("DefaultSessionSelector")
    @Autowired
    private SessionSelector sessionSelector;

    @Autowired
    private MessageInterceptorChain messageInterceptorChain;

    @Autowired
    private RequestIdGenerator requestIdGenerator;

    @PostConstruct
    public void postConstruct() {
        messageInterceptorChain.register(this);
    }

    @Override
    public Message exchange(GenericMessage<?> msg, long timeout, TimeUnit unit) throws Exception {
        var future = new CompletableFuture<Message>();
        msg.setId(requestIdGenerator.nextId());
        futures.putIfAbsent(msg.getId(), future);
        var session = sessionSelector.from(msg);
        log.info("Sending message to {}", session.getRemoteAddress());
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(msg)));
        try {
            return future.get(timeout, unit);
        } catch (TimeoutException e) {
            log.info("Exchange message {} timeout.", msg);
            throw e;
        }
    }

    @Override
    public Message exchange(GenericMessage<?> msg) throws Exception {
        return exchange(msg, 1, TimeUnit.MINUTES);
    }

    @Override
    public Message intercept(WebSocketSession session, Message message) {
        var f = futures.get(message.getId());
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
