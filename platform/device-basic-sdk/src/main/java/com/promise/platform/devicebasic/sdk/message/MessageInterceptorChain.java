package com.promise.platform.devicebasic.sdk.message;

import org.springframework.web.socket.WebSocketSession;

/**
 * A message interceptor chain.
 * <p/>
 * Message will be feed to one or more interceptors on the chain.
 */
public interface MessageInterceptorChain extends Iterable<MessageInterceptor> {

    /**
     * Register a interceptor to this chain.
     *
     * @param interceptor the one to register.
     */
    void register(MessageInterceptor interceptor);

    void intercept(WebSocketSession session, Message message);
}
