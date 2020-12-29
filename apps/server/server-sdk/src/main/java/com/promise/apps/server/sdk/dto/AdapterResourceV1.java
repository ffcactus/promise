package com.promise.apps.server.sdk.dto;

import com.promise.platform.sdk.dto.BasicResourceResponseV1;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents the response of adapter resource.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AdapterResourceV1 extends BasicResourceResponseV1 {
    public AdapterSummaryV1 summary;
    public List<NetworkAdapterV1> instances;

    /**
     * Represents network adapter in {@link AdapterResourceV1}.
     */
    @Data
    @NoArgsConstructor
    public static class NetworkAdapterV1 {
        public String name;
        public String model;
        public HardwareStatusV1 hardwareStatus;
        public List<NetworkAdapterControllerV1> controllers;

        /**
         * Represents network adapter in {@link NetworkAdapterV1}.
         */
        @Data
        @NoArgsConstructor
        public static class NetworkAdapterControllerV1 {
            public List<NetworkPortV1> networkPorts;

            /**
             * Represents the network port in {@link NetworkAdapterControllerV1}.
             */
            @Data
            @NoArgsConstructor
            public static class NetworkPortV1 {
                public String physicalPortNumber;
                public String linkStatus;
                public List<String> associatedNetworkAddresses;
            }

        }

    }
}
