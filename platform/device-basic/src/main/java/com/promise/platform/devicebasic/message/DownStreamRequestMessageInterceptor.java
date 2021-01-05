package com.promise.platform.devicebasic.message;

import com.promise.platform.devicebasic.executor.CollectorRegisterAction;
import com.promise.platform.devicebasic.sdk.message.Message;
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

/**
 * The collector request message interceptor.
 * <p/>
 * This interceptor should be the last interceptor in the chain.
 */
@Component
public class DownStreamRequestMessageInterceptor implements MessageInterceptor {
    private static final Logger log = LoggerFactory.getLogger(DownStreamRequestMessageInterceptor.class);
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Autowired
    private MessageInterceptorChain messageInterceptorChain;

    @PostConstruct
    public void postConstruct() {
        messageInterceptorChain.register(this);
    }

    @Override
    public Message intercept(WebSocketSession session, Message message) {
        log.info("intercepting message, id = {}, command = {}.", message.getId(), message.getCommand());
        switch (message.getCommand()) {
            case CollectorRegister:
                executorService.submit(new CollectorRegisterAction(session, message));
                break;
            default:
                break;
        }
        return null;
    }

    @Override
    public int order() {
        return 1;
    }
}
