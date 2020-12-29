package com.promise.collector.realtime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

/**
 * The default implementation of {@link RealtimeCollector}
 */
@Component
public class DefaultRealtimeCollector implements RealtimeCollector {
    private final static Logger log = LoggerFactory.getLogger(DefaultRealtimeCollector.class);

    @Override
    public void start(WebSocketSession session) {
        log.info("Real time collector started.");
    }

    @Override
    public void stop() {
        log.info("Real time collector stopped.");
    }
}
