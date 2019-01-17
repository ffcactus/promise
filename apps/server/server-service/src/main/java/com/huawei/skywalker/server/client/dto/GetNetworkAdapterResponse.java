package com.huawei.skywalker.server.client.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.huawei.skywalker.server.model.AdapterResource.NetworkAdapter;
import com.huawei.skywalker.server.model.AdapterResource.NetworkAdapter.NetworkAdapterController;
import com.promise.apps.server.sdk.dto.HardwareStatusV1;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response of network adapter from server's Redfish API.
 *
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetNetworkAdapterResponse
{
    public String id;
    public String name;
    public String manufacturer;
    public String model;
    public HardwareStatusV1 status;
    public List<NetworkAdapterControllerResponse> controllers;

    /**
     * Represents the response of network adapter controller from server's
     * Redfish API.
     *
     */
    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NetworkAdapterControllerResponse
    {
        public Link link;

        @Data
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Link
        {
            public List<OdataId> networkPorts;
        }
    }

    /**
     * Convert the DTO to model.
     * 
     * @param controllers The network adapter controller model list.
     * @return The module.
     */
    public NetworkAdapter convert(List<NetworkAdapterController> controllers)
    {
        NetworkAdapter ret = new NetworkAdapter();
//        ret.id = this.id;
//        ret.name = this.name;
//        ret.manufacturer = this.manufacturer;
//        ret.model = this.model;
//        ret.status = this.status;
//        ret.controllers = controllers;
        return ret;
    }
}
