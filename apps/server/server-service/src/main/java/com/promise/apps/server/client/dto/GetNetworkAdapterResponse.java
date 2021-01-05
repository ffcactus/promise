package com.promise.apps.server.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.promise.apps.server.model.AdapterResource;
import com.promise.apps.server.sdk.dto.HardwareStatusV1;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents the response of network adapter from server's Redfish API.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetNetworkAdapterResponse {
    public String id;
    public String name;
    public String manufacturer;
    public String model;
    public HardwareStatusV1 status;
    public List<NetworkAdapterControllerResponse> controllers;

    /**
     * Represents the response of network adapter controller from server's
     * Redfish API.
     */
    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NetworkAdapterControllerResponse {
        public Link link;

        @Data
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Link {
            public List<OdataId> networkPorts;
        }
    }

    /**
     * Convert the DTO to model.
     *
     * @param controllers The network adapter controller model list.
     * @return The module.
     */
    public AdapterResource.NetworkAdapter convert(List<AdapterResource.NetworkAdapter.NetworkAdapterController> controllers) {
        AdapterResource.NetworkAdapter ret = new AdapterResource.NetworkAdapter();
//        ret.id = this.id;
//        ret.name = this.name;
//        ret.manufacturer = this.manufacturer;
//        ret.model = this.model;
//        ret.status = this.status;
//        ret.controllers = controllers;
        return ret;
    }
}
