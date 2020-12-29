package com.promise.platform.devicebasic.exception;

import com.promise.platform.common.dto.ErrorResponseArgumentType;
import com.promise.platform.common.dto.ErrorResponseArgumentV1;
import com.promise.platform.common.exception.PromiseException;

import java.util.Collections;

public class DeviceAlreadyExistException extends PromiseException {

    public DeviceAlreadyExistException(Long id) {
        super(
                "device.exception.already-exist",
                "A device with ID {} already exist",
                Collections.singletonList(new ErrorResponseArgumentV1(ErrorResponseArgumentType.Text, Long.toString(id))),
                null
        );
    }
}
