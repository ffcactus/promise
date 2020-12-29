package com.promise.platform.devicebasic.sdk.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is an another type of {@link Message} that wrap the payload as a generic type.
 * This class is more convenient before serialization.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class GenericMessage<T> {
    private String id;
    private MessageCommand command;
    private Long deviceId;
    private T payload;

    @Override
    public String toString() {
        return "GenericMessage{" +
                "id='" + id + '\'' +
                ", command=" + command +
                ", deviceId=" + deviceId +
                '}';
    }
}
