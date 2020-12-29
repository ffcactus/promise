package com.promise.collector.message;

/**
 * A observer for the connection.
 */
public interface ConnectionObserver {
    /**
     * The callback when the connection is closed.
     */
    void onClose();

    /**
     * The callback when the connection is opened.
     */
    void onOpen();
}
