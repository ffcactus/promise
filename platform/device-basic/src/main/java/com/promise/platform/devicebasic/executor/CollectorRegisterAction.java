package com.promise.platform.devicebasic.executor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.promise.platform.common.ApplicationContextUtils;
import com.promise.platform.devicebasic.entity.EntityConverter;
import com.promise.platform.devicebasic.exception.CollectorIdentityException;
import com.promise.platform.devicebasic.sdk.dto.collector.GetCollectorResponseV1;
import com.promise.platform.devicebasic.sdk.message.GenericMessage;
import com.promise.platform.devicebasic.sdk.message.Message;
import com.promise.platform.devicebasic.sdk.ws.CollectorRegisterRequestV1;
import com.promise.platform.devicebasic.service.CollectorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.Callable;

public class CollectorRegisterAction implements Callable<Void> {
    private static final Logger log = LoggerFactory.getLogger(CollectorRegisterAction.class);
    private final Message requestMessage;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CollectorService collectorService;
    private final WebSocketSession session;

    public CollectorRegisterAction(WebSocketSession session, Message message) {
        this.requestMessage = message;
        this.session = session;
        collectorService = ApplicationContextUtils.getApplicationContext().getBean(
                CollectorService.class
        );
    }

    @Override
    public Void call() throws CollectorIdentityException {
        var request = objectMapper.convertValue(requestMessage.getPayload(), CollectorRegisterRequestV1.class);
        var entity = collectorService.register(request, session);
        var respMessage = new GenericMessage<GetCollectorResponseV1>();
        respMessage.setId(requestMessage.getId());
        respMessage.setCommand(requestMessage.getCommand());
        respMessage.setPayload(EntityConverter.toCollectorResponse(entity));
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(respMessage)));
            log.info("Send response message = {}", respMessage);
        } catch (Exception e) {
            log.warn("Failed to send response", e);
        }
        return null;
    }
}
