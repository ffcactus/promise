package com.promise.collector.state;

import com.promise.collector.executor.RegisterAction;
import com.promise.collector.message.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The state machine that listen on the {@link CollectorStateEvent} and do both state change and operation accordingly.
 *
 */
@Component
public class StateMachine {
    private static final Logger log = LoggerFactory.getLogger(StateMachine.class);
    private volatile CollectorState current = CollectorState.Started;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Autowired
    private ConnectionManager connectionManager;

    @EventListener
    public void onStateChange(CollectorStateEvent event) {
        log.info("process event begin, event = {}", event);
        switch (event) {
            case ApplicationStarted:
                current = CollectorState.Connecting;
                log.info("Application started, current state = {}", current);
                connectionManager.connect();
                break;
            case WsConnectionTimeout:
                current = CollectorState.Connecting;
                log.info("Connection timeout, reconnect, current state = {}", current);
                connectionManager.connect();
                break;
            case WsConnected:
                current = CollectorState.Registering;
                log.info("Connection is build, do registration, current state = {}", current);
                executorService.submit(new RegisterAction());
                break;
            case WsConnectionTransportError:
                log.info("Connection transport error.");
                break;
            case WsConnectionLost:
                current = CollectorState.Connecting;
                log.info("Connection is lost, reconnect, current state = {}", current);
                connectionManager.connect();
                break;
            case RegisterSuccess:
                current = CollectorState.Working;
                log.info("Register success, current state = {}", current);
                break;
            case RegisterFailure:
                if (current == CollectorState.Connected) {
                    current = CollectorState.Registering;
                    log.info("Register failure, redo registration, current state = {}", current);
                    executorService.submit(RegisterAction::new);
                } else {
                    log.info("Register failure, current state = {}", current);
                }
                break;
            default:
                break;
        }
        log.info("process event end, event = {}", event);
    }
}
