package com.promise.collector.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

/**
 * Session publisher.
 * <p>
 * Components using the {@link org.springframework.web.socket.WebSocketSession} should listen and use it.
 */
@Component
public class SessionPublisher implements ApplicationEventPublisherAware {
    private final static Logger log = LoggerFactory.getLogger(SessionPublisher.class);
    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publish(WebSocketSession session) {
        publisher.publishEvent(session);
        log.info("Session is published.");
    }
}
