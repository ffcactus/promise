package com.promise.collector.message;

import com.promise.collector.executor.DeviceDiscoverAction;
import com.promise.collector.monitor.MonitorService;
import com.promise.collector.plugin.PluginManager;
import com.promise.platform.devicebasic.sdk.message.Message;
import com.promise.platform.devicebasic.sdk.message.MessageCommand;
import com.promise.platform.devicebasic.sdk.message.MessageInterceptor;
import com.promise.platform.devicebasic.sdk.message.MessageInterceptorChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class UpperStreamRequestMessageInterceptor implements MessageInterceptor {
    private static final Logger log = LoggerFactory.getLogger(UpperStreamRequestMessageInterceptor.class);
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Autowired
    private MessageInterceptorChain messageInterceptorChain;

    @Autowired
    private MonitorService monitorService;

    @Autowired
    private PluginManager pluginManager;

    @PostConstruct
    public void postConstruct() {
        messageInterceptorChain.register(this);
    }

    @Override
    public Message intercept(WebSocketSession session, Message message) {
        try {
            log.info("intercepting message, id = {}, command = {}.", message.getId(), message.getCommand());
            MessageCommand cmd = message.getCommand();
            switch (cmd) {
                case DeviceDiscover:
                    executorService.submit(new DeviceDiscoverAction(session, message, pluginManager));
                    break;
                case DeviceMonitor:
                    monitorService.onMonitorMessage(session, message);
                    break;
                default:
                    log.info("Unknown command {}.", cmd);
                    return message;
            }
        } catch (Exception e) {
            log.warn("Handle message failed", e);
        }
        return null;
    }

    @Override
    public int order() {
        return 1;
    }
}
