package com.huawei.skywalker.server.model;

import java.util.List;
import java.util.stream.Collectors;

import com.huawei.skywalker.server.model.Server.AdapterSummary;
import com.promise.apps.server.sdk.dto.AdapterResourceV1;
import com.promise.apps.server.sdk.dto.AdapterResourceV1.NetworkAdapterV1;
import com.promise.apps.server.sdk.dto.AdapterResourceV1.NetworkAdapterV1.NetworkAdapterControllerV1;
import com.promise.apps.server.sdk.dto.AdapterResourceV1.NetworkAdapterV1.NetworkAdapterControllerV1.NetworkPortV1;
import com.promise.apps.server.sdk.dto.HardwareStatusV1;
import com.promise.platform.sdk.model.BasicResource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents the adapter resource of a server.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class AdapterResource extends BasicResource
{
    public AdapterSummary summary;
    public List<NetworkAdapter> instances;
    
    /**
     * Convert model to response DTO.
     * 
     * @param model The model object.
     * @return The response DTO object.
     */
    public static AdapterResourceV1 toResponse(AdapterResource model)
    {
        var ret = new AdapterResourceV1();
        BasicResource.model2dto(ret, model);
        ret.summary = AdapterSummary.toResponse(model.summary);
        ret.instances = model.instances.stream().map(NetworkAdapter::toResponse).collect(Collectors.toList());
        return ret;
    }
    
    /**
     * Represents the network adapter controller in {@link AdapterResource}.
     *
     */
    @Data
    @NoArgsConstructor
    public static class NetworkAdapter
    {
        public String id;
        public String name;
        public String model;
        public HardwareStatusV1 hardwareStatus;
        public List<NetworkAdapterController> controllers;

        /**
         * Convert the model to DTO.
         * @return DTO object.
         */
        public static NetworkAdapterV1 toResponse(NetworkAdapter model) {
            var ret = new NetworkAdapterV1();
            ret.name = model.name;
            ret.model = model.model;
            ret.hardwareStatus = model.hardwareStatus;
            ret.controllers = model.controllers.stream().map(NetworkAdapterController::toResponse).collect(Collectors.toList());
            return ret;
        }
        
        /**
         * Represents the network adapter controller in {@link NetworkAdapter}.
         *
         */
        @Data
        @NoArgsConstructor
        public static class NetworkAdapterController
        {
            public List<NetworkPort> networkPorts;
            
            /**
             * Convert the model to DTO.
             * @return DTO object.
             */
            public static NetworkAdapterControllerV1 toResponse(NetworkAdapterController model) {
                var ret = new NetworkAdapterControllerV1();
                ret.networkPorts = model.networkPorts.stream().map(NetworkPort::toResponse).collect(Collectors.toList());
                return ret;
            }
                        
            /**
             * Represents the network port in {@link NetworkAdapterController}.
             *
             */
            @Data
            @NoArgsConstructor
            public static class NetworkPort
            {
                public String physicalPortNumber;
                public String linkStatus;
                public List<String> associatedNetworkAddresses;
                
                /**
                 * Convert the model to DTO.
                 * @return DTO object.
                 */
                public static NetworkPortV1 toResponse(NetworkPort model) {
                    var ret = new NetworkPortV1();
                    ret.physicalPortNumber = model.physicalPortNumber;
                    ret.linkStatus = model.linkStatus;
                    ret.associatedNetworkAddresses = model.associatedNetworkAddresses;
                    return ret;
                }
            }
        }
    }
}
