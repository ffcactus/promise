package com.promise.platform.devicebasic.repository;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
// TODO remove
public class SessionRepository {

    private final Map<Long, WebSocketSession> sessions = new ConcurrentHashMap<>();

    public void addSession(Long id, WebSocketSession session) {
        sessions.put(id, session);
    }

    public void removeSession(WebSocketSession session) {
        sessions.forEach((k, v) -> {
            if (Objects.equals(v.getRemoteAddress(), session.getRemoteAddress())) {
                sessions.remove(k);
            }
        });
    }
}
