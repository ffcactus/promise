package com.promise.collector.message;

/**
 * Connection manager that can do connect and check the connection state.
 */
public interface ConnectionManager {
    void connect();

    ConnectionState getConnectionState();

    long openingAt();
}
