package com.promise.collector.state;

/**
 * The possible state of a collector.
 */
public enum CollectorState {

    /**
     * The state when the application started.
     */
    Started,

    /**
     * The state in which the collector is trying to connect to upper stream.
     */
    Connecting,

    /**
     * The state in which the collector try to do connect to upper stream but timeout.
     */
    ConnectingTimeout,

    /**
     * The state in which the collector is connected to the upper stream.
     * If the connection is lost later, it should move to {@link CollectorState#Connecting}
     */
    Connected,

    /**
     * The state in which the collector is trying to register itself to upper stream.
     * If the registration is successed, it should move to {@link CollectorState#Working} or
     * {@link CollectorState#Upgrading}
     */
    Registering,

    /**
     * The state in which the collector is working normally.
     * If the connection is lost, it should move to {@link CollectorState#Connecting}
     */
    Working,

    /**
     * The state in which the collector is suspended by upper stream.
     * If the suspend is over, it should move to {@link CollectorState#Working}
     */
    Suspend,

    /**
     * The state in which the collector is upgrading.
     * After the upgrading is done, it should restart.
     */
    Upgrading,
}
