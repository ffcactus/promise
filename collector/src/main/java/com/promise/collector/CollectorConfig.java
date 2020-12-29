package com.promise.collector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class CollectorConfig {

    @Value("${collector.name:unknown}")
    private String collectorName;

    @Value("${collector.sn:unknown}")
    private String collectorSn;

    @Value("${collector.version:unknown}")
    private String collectorVersion;

    @Bean
    public CollectorIdentity collectorIdentity() throws UnknownHostException {
        var id = new CollectorIdentity();
        id.setName(collectorName);
        id.setSn(collectorSn);
        id.setIp(InetAddress.getLocalHost().getHostAddress());
        id.setVersion(collectorVersion);
        return id;
    }
}
