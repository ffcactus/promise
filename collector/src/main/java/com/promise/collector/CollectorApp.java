package com.promise.collector;

import com.promise.collector.state.StatePublisher;
import com.promise.collector.state.CollectorStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.promise"})
public class CollectorApp implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger log = LoggerFactory.getLogger(CollectorApp.class);

    @Autowired
    private CollectorIdentity id;

    @Autowired
    private StatePublisher statePublisher;

    public static void main(String[] args) {
        SpringApplication.run(CollectorApp.class, args);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("Collector application started, ip = {}, name = {}, sn = {}", id.getIp(), id.getName(), id.getSn());
        statePublisher.publish(CollectorStateEvent.ApplicationStarted);
    }
}
