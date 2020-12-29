package com.promise.platform.devicebasic.sdk.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public abstract class AbstractMessageInterceptorChain implements MessageInterceptorChain {
    private static final Logger log = LoggerFactory.getLogger(AbstractMessageInterceptorChain.class);
    private final SortedSet<MessageInterceptor> sets;

    public AbstractMessageInterceptorChain() {
        sets = new TreeSet<>();
    }

    @Override
    public void register(MessageInterceptor interceptor) {
        sets.add(interceptor);
    }

    @Override
    public Iterator<MessageInterceptor> iterator() {
        return sets.iterator();
    }

    @Override
    public void intercept(WebSocketSession session, Message message) {
        for (var interceptor : sets) {
            message = interceptor.intercept(session, message);
            if (message == null) {
                log.info("Message consumed by {}", interceptor.getClass().toString());
                return;
            }
        }
    }
}
