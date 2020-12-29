package com.promise.collector.realtime;

import org.springframework.web.socket.WebSocketSession;

/**
 * The collector that collect the real time information for the devices that is managed by this collector.
 */
public interface RealtimeCollector {

    /**
     * Start the collection.
     *
     * @param session
     */
    void start(WebSocketSession session);

    /**
     * Stop the collection.
     */
    void stop();
}
