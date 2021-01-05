package com.promise.platform.devicebasic.sdk.message;

import org.springframework.web.socket.WebSocketSession;

/**
 * The message interceptor may consume the message, if the message is consumed the {@link MessageInterceptor#intercept}
 * should return null. Or the message may return with or without change.
 */
public interface MessageInterceptor extends Comparable<MessageInterceptor> {

    /**
     * Intercept the message, consume it or pass a message to the next consumer.
     *
     * @param session the session associated with the message.
     * @param message the message to intercept.
     * @return the message to the next consumer or null.
     */
    Message intercept(WebSocketSession session, Message message);

    /**
     * Return the nature order in the chain.
     *
     * @return the nature order in the chain.
     */
    int order();

    @Override
    default int compareTo(MessageInterceptor o) {
        return Integer.compare(order(), o.order());
    }
}
