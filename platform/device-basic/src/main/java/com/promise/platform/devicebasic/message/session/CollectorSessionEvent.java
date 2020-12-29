package com.promise.platform.devicebasic.message.session;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.socket.WebSocketSession;

@Data
@NoArgsConstructor
@AllArgsConstructor
// TODO remove.
public class CollectorSessionEvent {
    private Long collectorId;
    private CollectorSessionEventType type;
    private WebSocketSession session;
}
