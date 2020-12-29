package com.promise.collector.message;

import com.promise.collector.CollectorIdentity;
import com.promise.platform.devicebasic.sdk.message.RequestIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutoIncreaseRequestIdGenerator implements RequestIdGenerator {
    private long id;

    @Autowired
    private CollectorIdentity collectorIdentity;

    /**
     * Return the next ID for the next request.
     *
     * @return
     */
    public synchronized String nextId() {
        return collectorIdentity.getSn() + Long.toString(id++);
    }
}
