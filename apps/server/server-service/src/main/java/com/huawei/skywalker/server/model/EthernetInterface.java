package com.huawei.skywalker.server.model;

import com.promise.apps.server.sdk.dto.EthernetInterfaceV1;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the Ethernet interface on OS of the server.
 *
 */
@Data
@NoArgsConstructor
public class EthernetInterface
{
    /**
     * Convert the model to DTO.
     * @return DTO object.
     */
    public static EthernetInterfaceV1 toResponse(EthernetInterface model) {
        var ret = new EthernetInterfaceV1();
        // TODO
        return ret;
    }
}
