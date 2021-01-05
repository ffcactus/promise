package com.promise.collector.state;

/**
 * The events that may cause the collector state change.
 */
public enum CollectorStateEvent {
    ApplicationStarted,
    WsConnected,
    WsConnectionTimeout,
    WsConnectionTransportError,
    WsConnectionLost,
    RegisterSuccess,
    RegisterFailure,
}
