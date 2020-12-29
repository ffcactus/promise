package com.promise.platform.devicebasic.message;

import com.promise.platform.devicebasic.sdk.message.RequestIdGenerator;
import org.springframework.stereotype.Component;

/**
 * An ID request ID generator in the single instance environment.
 */
@Component
public class SingleInstanceRequestIdGenerator implements RequestIdGenerator {

    private long id;

    /**
     * Return the next ID for the next request.
     *
     * @return
     */
    public synchronized String nextId() {
        return Long.toString(id++);
    }
}
