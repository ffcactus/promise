package com.promise.platform.devicebasic.message.session;

import com.promise.platform.devicebasic.exception.NoCollectorException;
import com.promise.platform.devicebasic.sdk.message.GenericMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * A selector that selects the right WebSocket session from various input.
 */
public interface SessionSelector {

    /**
     * Select a session according to a {@link GenericMessage}
     *
     * @param msg The message according to which the session will be selected.
     * @return The session for this message.
     */
    WebSocketSession from(GenericMessage<?> msg) throws NoCollectorException;
}
