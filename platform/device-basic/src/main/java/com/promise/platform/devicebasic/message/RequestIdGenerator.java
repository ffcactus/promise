package com.promise.platform.devicebasic.message;

/**
 * The request ID generator.
 * Each request sending to exchange should have a unique request ID.
 */
public interface RequestIdGenerator {

    /**
     * Generate the next request ID.
     *
     * @return The next request ID.
     */
    String nextId();
}
