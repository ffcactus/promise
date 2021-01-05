package com.promise.platform.devicebasic.message.session;

import com.promise.platform.common.entity.MappingEntity;
import com.promise.platform.devicebasic.exception.NoCollectorException;
import com.promise.platform.devicebasic.repository.CollectorGroupMemberRepository;
import com.promise.platform.devicebasic.repository.RecordedCollectorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * The repository for current exist collector session.
 */
@Component
public class CollectorSessionRepository {

    private static final Logger log = LoggerFactory.getLogger(CollectorSessionRepository.class);

    private final Map<Long, WebSocketSession> sessionsByCollectorId = new ConcurrentHashMap<>();

    @Autowired
    private CollectorGroupMemberRepository collectorGroupMemberRepository;

    @Autowired
    private RecordedCollectorRepository recordedCollectorRepository;

    /**
     * Add a session.
     *
     * @param collectorId the collector ID of this session.
     * @param session     the session itself.
     */
    public void addSession(Long collectorId, WebSocketSession session) {
        sessionsByCollectorId.put(collectorId, session);
        log.info("Session added. collector ID = {}, session = {}", collectorId, session.getRemoteAddress());
    }

    /**
     * Remove the session.
     *
     * @param session the session to remove.
     * @return the collector ID associated to this session. Return null if the session doesn't exist.
     */
    public Long removeSession(WebSocketSession session) {
        for (var entry : sessionsByCollectorId.entrySet()) {
            Long k = entry.getKey();
            WebSocketSession v = entry.getValue();
            if (Objects.equals(v.getRemoteAddress(), session.getRemoteAddress())) {
                sessionsByCollectorId.remove(k);
                log.info("Session removed. collector ID = {}, session = {}", k, session.getRemoteAddress());
                return k;
            }
        }
        log.warn("Remove session failed, session doesn't exist. session = {}", session.getRemoteAddress());
        return null;
    }

    /**
     * Find out all the sessions that belongs to a collector group.
     *
     * @param collectGroupId collector group ID.
     * @return the sessions.
     */
    List<WebSocketSession> getSessionByCollectorGroupId(Long collectGroupId) {
        // Find the collectors that belongs to the group ID.
        var mappings = collectorGroupMemberRepository.findByLeftId(collectGroupId);
        // For each if the collector, check if it is connected, if it is connected, save the ID to the list.
        var collectorIds = mappings.stream().filter(mapping -> {
            var collector = recordedCollectorRepository.findById(mapping.getRightId());
            return collector.isPresent() && collector.get().isConnected();
        }).map(MappingEntity::getRightId).collect(Collectors.toList());
        List<WebSocketSession> ret = new ArrayList<>();
        sessionsByCollectorId.forEach((k, v) -> {
            if (collectorIds.contains(k)) {
                ret.add(v);
            }
        });
        return ret;
    }

    /**
     * Find the session for a device.
     *
     * @param id device ID.
     * @return the session.
     * @throws NoCollectorException in case no session for this device.
     */
    // TODO move this function to service?
    WebSocketSession getSessionByDeviceId(Long id) throws NoCollectorException {
        var opt = recordedCollectorRepository.findById(id);
        if (opt.isEmpty()) {
            throw new NoCollectorException("device has no collector");
        }
        var collector = opt.get();
        if (!collector.isConnected()) {
            throw new NoCollectorException("collector lost");
        }
        var session = sessionsByCollectorId.get(collector.getId());
        if (session == null) {
            throw new NoCollectorException("no session, data not updated.");
        }
        return session;
    }
}
