package com.promise.collector.executor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.promise.collector.CollectorIdentity;
import com.promise.collector.state.StatePublisher;
import com.promise.platform.common.ApplicationContextUtils;
import com.promise.platform.devicebasic.sdk.dto.collector.GetCollectorResponseV1;
import com.promise.platform.devicebasic.sdk.message.Exchanger;
import com.promise.platform.devicebasic.sdk.message.GenericMessage;
import com.promise.platform.devicebasic.sdk.message.RequestIdGenerator;
import com.promise.platform.devicebasic.sdk.ws.CollectorRegisterRequestV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

import static com.promise.collector.state.CollectorStateEvent.RegisterFailure;
import static com.promise.collector.state.CollectorStateEvent.RegisterSuccess;
import static com.promise.platform.devicebasic.sdk.message.MessageCommand.CollectorRegister;

public class RegisterAction implements Callable<Void> {
    private static final Logger log = LoggerFactory.getLogger(RegisterAction.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Exchanger exchanger;
    private final RequestIdGenerator requestIdGenerator;
    private final CollectorIdentity collectorIdentity;
    private final StatePublisher statePublisher;

    public RegisterAction() {
        var ctx = ApplicationContextUtils.getApplicationContext();
        exchanger = ctx.getBean(Exchanger.class);
        requestIdGenerator = ctx.getBean(RequestIdGenerator.class);
        collectorIdentity = ctx.getBean(CollectorIdentity.class);
        statePublisher = ctx.getBean(StatePublisher.class);
    }

    @Override
    public Void call() {
        log.info("action started.");
        var message = new GenericMessage<CollectorRegisterRequestV1>();
        var payload = new CollectorRegisterRequestV1();
        payload.setIp(collectorIdentity.getIp());
        payload.setName(collectorIdentity.getName());
        payload.setSn(collectorIdentity.getSn());
        payload.setVersion(collectorIdentity.getVersion());

        message.setPayload(payload);
        message.setId(requestIdGenerator.nextId());
        message.setCommand(CollectorRegister);
        try {
            var exchangeResp = exchanger.exchange(message);
            var resp = objectMapper.convertValue(exchangeResp.getPayload(), GetCollectorResponseV1.class);
            log.info("Register to upper stream success, first registration at = {}", resp.getCreateAt());
            statePublisher.publish(RegisterSuccess);
        } catch (Exception e) {
            log.warn("Register to upper stream failed.", e);
            statePublisher.publish(RegisterFailure);
        }
        log.info("action end.");
        return null;
    }
}
