package com.promise.collector.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A connection observer that helps to do reconnect.
 */
// @Component
public class AutoReconnectConnectionObserver {
    private final ConnectionManager connectionManager;
    private static final Logger log = LoggerFactory.getLogger(AutoReconnectConnectionObserver.class);
    private final ScheduledExecutorService executorService;
    private long maxOpenDelayNanoSecond;
    private static final long DefaultOpenDelayNumber = 5L * 1000 * 1000 * 1000;

    @Value("${remote.webSocket.maxOpenDelay}")
    private String maxOpenDelayNanoSecondString;

    @Autowired
    public AutoReconnectConnectionObserver(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        executorService = Executors.newScheduledThreadPool(1);
    }

    public void start() {
        try {
            maxOpenDelayNanoSecond = Integer.parseInt(maxOpenDelayNanoSecondString) * 1000L * 1000 * 1000;
        } catch (NumberFormatException e) {
            maxOpenDelayNanoSecond = DefaultOpenDelayNumber;
        }

        executorService.scheduleWithFixedDelay(() -> {
            switch (connectionManager.getConnectionState()) {
                case Closed:
                    connectionManager.connect();
                    break;
                case Opening:
                    if (System.nanoTime() - connectionManager.openingAt() > maxOpenDelayNanoSecond) {
                        connectionManager.connect();
                    }
                    break;
                default:
                    break;
            }
        }, 0, 5, TimeUnit.SECONDS);
    }
}
