package com.promise.collector.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class StatePublisher implements ApplicationEventPublisherAware {
    private static final Logger log = LoggerFactory.getLogger(StatePublisher.class);
    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    /**
     * Report the event so that the state machine can change the state accordingly.
     * @param event the event to report.
     */
    public synchronized void publish(CollectorStateEvent event) {
        log.info("Publishing {}.", event);
        publisher.publishEvent(event);
    }
}
