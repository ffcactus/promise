package com.promise.apps.server.model;

import com.promise.apps.server.sdk.dto.NetworkInterfaceV1;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the network interface on the OS of the server.
 */
@Data
@NoArgsConstructor
public class NetworkInterface {
    /**
     * Convert the model to DTO.
     *
     * @return DTO object.
     */
    public static NetworkInterfaceV1 toResponse(NetworkInterface model) {
        var ret = new NetworkInterfaceV1();
        // TODO
        return ret;
    }
}
