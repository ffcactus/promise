package com.promise.platform.devicebasic.sdk.ws;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The request for collector registration.
 *
 */
@Data
@NoArgsConstructor
public class CollectorRegisterRequestV1 {
    private String name;
    private String ip;
    private String sn;
    private String version;
}
