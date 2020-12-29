package com.promise.platform.devicebasic.sdk.message;

import org.springframework.web.socket.WebSocketSession;

/**
 * The message processor.
 * <p>
 * Generally a message processor should process the message.
 *
 */
public interface MessageProcessor {
    void process(WebSocketSession session, Message message);
}
