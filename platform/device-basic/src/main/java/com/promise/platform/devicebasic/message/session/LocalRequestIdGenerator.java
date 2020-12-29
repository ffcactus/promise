package com.promise.platform.devicebasic.message.session;

import com.promise.platform.devicebasic.message.RequestIdGenerator;
import org.springframework.stereotype.Component;

@Component
public class LocalRequestIdGenerator implements RequestIdGenerator {

    private long nextId = 0;

    @Override
    public String nextId() {
        return "upperstream_" + nextId++;
    }
}
