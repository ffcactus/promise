package com.promise.platform.devicebasic.message.session;

import com.promise.platform.devicebasic.exception.NoCollectorException;
import com.promise.platform.devicebasic.sdk.dto.device.DiscoverDeviceRequestV1;
import com.promise.platform.devicebasic.sdk.message.GenericMessage;
import com.promise.platform.devicebasic.sdk.message.MessageCommand;
import com.promise.platform.devicebasic.service.AdviceQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

/**
 * The default session selector that is suitable for most of the case.
 * <p/>
 * Device and collector are both grouped. There is a mapping from device group to the collector group.
 * This {@code SessionSelector} will selector the session according to this mapping, that is get the device's group
 * and find the collector group, then pick the right session.
 *
 */
@Component("DefaultSessionSelector")
public class DefaultSessionSelector implements SessionSelector {

    @Autowired
    private CollectorSessionRepository collectorSessionRepository;

    @Autowired
    private AdviceQueryService adviceQueryService;

    @Override
    public WebSocketSession from(GenericMessage<?> msg) throws NoCollectorException {
        Long collectorGroupId;

        // For discovery, the group ID is specified in the request.
        if (msg.getCommand().equals(MessageCommand.DeviceDiscover)) {
            var request = (DiscoverDeviceRequestV1) msg.getPayload();
            collectorGroupId = request.getCollectorGroupId();
        } else {
            collectorGroupId = adviceQueryService.getCollectorGroupIdByDeviceId(msg.getDeviceId());
        }

        // Find all the possible sessions.
        var sessions = collectorSessionRepository.getSessionByCollectorGroupId(collectorGroupId);
        if (sessions.isEmpty()) {
            throw new NoCollectorException();
        }

        // For discovery, we pick up a session base on the overall hash of the request.
        if (msg.getCommand().equals(MessageCommand.DeviceDiscover)) {
            var request = (DiscoverDeviceRequestV1) msg.getPayload();
            return sessions.get(sessions.size() % hashOf(request) - 1);
        }
        // For others, we pick up a session base on device Id.
        return sessions.get(sessions.size() % hashOf(msg.getDeviceId()) - 1);
        // TODO update cache.
    }

    private int hashOf(DiscoverDeviceRequestV1 request) {
        return request.getAddress().hashCode() & 0xfffffff;
    }

    private int hashOf(Long id) {return id.hashCode() & 0xfffffff;}
}
