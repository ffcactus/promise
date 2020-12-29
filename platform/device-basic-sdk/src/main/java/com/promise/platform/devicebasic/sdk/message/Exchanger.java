package com.promise.platform.devicebasic.sdk.message;

import java.util.concurrent.TimeUnit;

/**
 * The message exchanger for callers to send message synchronously.
 * <p>
 * The collector and upper stream should both have a exchanger so that they can intercommunication.
 *
 */
public interface Exchanger extends MessageInterceptor {
    Message exchange(GenericMessage<?> msg, long timeout, TimeUnit unit) throws Exception;

    Message exchange(GenericMessage<?> msg) throws Exception;
}
