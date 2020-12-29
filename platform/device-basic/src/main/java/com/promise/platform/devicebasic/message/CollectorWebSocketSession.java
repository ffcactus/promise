package com.promise.platform.devicebasic.message;


import org.springframework.web.socket.WebSocketSession;

import java.util.Objects;

class CollectorWebSocketSession implements Comparable<CollectorWebSocketSession> {
    private final WebSocketSession session;
    private final long connectedAt;

    public CollectorWebSocketSession(WebSocketSession session) {
        this.session = session;
        this.connectedAt = System.nanoTime();
    }

    @Override
    public int compareTo(CollectorWebSocketSession o) {
        return Long.compare(this.connectedAt, o.connectedAt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectorWebSocketSession that = (CollectorWebSocketSession) o;
        return connectedAt == that.connectedAt &&
                Objects.equals(
                        session.getRemoteAddress(),
                        that.session.getRemoteAddress()
                );
    }

    @Override
    public int hashCode() {
        return Objects.hash(session, connectedAt);
    }
}
