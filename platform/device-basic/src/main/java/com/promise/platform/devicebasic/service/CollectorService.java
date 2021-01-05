package com.promise.platform.devicebasic.service;

import com.promise.platform.common.dto.ListRequestV1;
import com.promise.platform.common.dto.ListResponseV1;
import com.promise.platform.common.util.PagingUtils;
import com.promise.platform.devicebasic.entity.CollectorEntity;
import com.promise.platform.devicebasic.entity.EntityConverter;
import com.promise.platform.devicebasic.exception.CollectorIdentityException;
import com.promise.platform.devicebasic.message.session.CollectorSessionRepository;
import com.promise.platform.devicebasic.repository.RecordedCollectorRepository;
import com.promise.platform.devicebasic.sdk.dto.collector.GetCollectorListItemV1;
import com.promise.platform.devicebasic.sdk.ws.CollectorRegisterRequestV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.WebSocketSession;

@Service
public class CollectorService {

    private static final Logger log = LoggerFactory.getLogger(CollectorService.class);

    @Autowired
    private RecordedCollectorRepository recordedCollectorRepository;

    @Autowired
    private CollectorSessionRepository collectorSessionRepository;

    public ListResponseV1<GetCollectorListItemV1> getAll(ListRequestV1 request) {
        return PagingUtils.from(
                recordedCollectorRepository.findAll(PagingUtils.from(request)),
                EntityConverter::toCollectorListItem
        );
    }

    /**
     * Handler collector register.
     * <p/>
     * If the register is registered before, just update it's state to online. Otherwise create a new one.
     *
     * @param request collector register request.
     * @param session the websocket session associated to this request.
     * @return the collector object.
     * @throws CollectorIdentityException if the collector's identity is missing.
     */
    @Transactional
    public CollectorEntity register(CollectorRegisterRequestV1 request, WebSocketSession session) throws CollectorIdentityException {
        if (request.getSn().isEmpty()) {
            log.warn("Receive a collector register request but sn is empty.");
            throw new CollectorIdentityException("sn is empty");
        }
        var current = recordedCollectorRepository.getFirstBySn(request.getSn());
        CollectorEntity ret;
        if (current.isPresent()) {
            ret = current.get();
            ret.setConnected(true);
            log.info("Registered collector is connected. name = {}, sn = {}, createAt = {}",
                    ret.getName(), ret.getSn(), ret.getCreateAt()
            );
            ret = recordedCollectorRepository.save(ret);
        } else {
            ret = new CollectorEntity();
            ret.setName(request.getName());
            ret.setIp(request.getIp());
            ret.setSn(request.getSn());
            ret.setVersion(request.getVersion());
            ret.setConnected(true);
            log.info("A new collector is connected. name = {}, sn = {}, createAt = {}",
                    ret.getName(), ret.getSn(), ret.getCreateAt()
            );
            ret = recordedCollectorRepository.save(ret);
        }
        collectorSessionRepository.addSession(ret.getId(), session);
        return ret;
    }

    public void handleConnectionLost(WebSocketSession session) {
        var collectorId = collectorSessionRepository.removeSession(session);
        if (collectorId != null) {
            var opt = recordedCollectorRepository.findById(collectorId);
            if (opt.isPresent()) {
                var collector = opt.get();
                collector.setConnected(false);
                recordedCollectorRepository.save(collector);
            }
        }
    }
}
