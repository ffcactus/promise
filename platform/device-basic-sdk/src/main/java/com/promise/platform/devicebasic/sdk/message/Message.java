package com.promise.platform.devicebasic.sdk.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This object represents the properties that should be included in the request and response between
 * upper stream service and collector.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class Message {
    /**
     * The unique ID in the whole messages.
     */
    private String id;
    private MessageCommand command;
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Long deviceId;
    private JsonNode payload;

    @Override
    @JsonIgnore
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", command=" + command +
                '}';
    }

    /**
     * Generate corresponding response message from this message.
     *
     * @param payload The payload in the response message.
     * @param <T>     The payload type.
     * @return The response message.
     */
    @JsonIgnore
    public <T> GenericMessage<T> toResponseMessage(T payload) {
        var ret = new GenericMessage<T>();
        ret.setId(id);
        ret.setDeviceId(deviceId);
        ret.setCommand(command);
        ret.setPayload(payload);
        return ret;
    }

    /**
     * Generate success response message from this message.
     *
     * @param payload The payload in the response message.
     * @param <T>     The payload type.
     * @return The response message.
     */
    @JsonIgnore
    public <T> GenericMessage<T> toSuccessMessage(T payload) {
        var ret = new GenericMessage<T>();
        ret.setId(id);
        ret.setDeviceId(deviceId);
        ret.setCommand(MessageCommand.Success);
        ret.setPayload(payload);
        return ret;
    }

    /**
     * Generate a failure response message from this message.
     *
     * @param payload The payload in the response message.
     * @param <T>     The payload type.
     * @return The response message.
     */
    @JsonIgnore
    public <T> GenericMessage<T> toFailureMessage(T payload) {
        var ret = new GenericMessage<T>();
        ret.setId(id);
        ret.setDeviceId(deviceId);
        ret.setCommand(MessageCommand.Failure);
        ret.setPayload(payload);
        return ret;
    }
}
