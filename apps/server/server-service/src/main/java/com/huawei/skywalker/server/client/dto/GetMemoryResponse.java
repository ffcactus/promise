package com.huawei.skywalker.server.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.huawei.skywalker.server.model.MemoryResource;
import com.huawei.skywalker.server.model.MemoryResource.Memory.MemoryLocation;
import com.promise.apps.server.sdk.dto.HardwareStatusV1;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response of memory from server's Redfish API.
 *
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetMemoryResponse
{
    public String id;
    public String name;
    public Integer capacityMiB;
    public String manufacturer;
    public Integer operatingSpeedMhz;
    public String serialNumber;
    public String memoryDeviceType;
    public Integer dataWidthBits;
    public Integer rankCount;
    public String partNumber;
    public String deviceLocator;
    public String baseModuleType;
    public MemoryLocationResponse memoryLocation;
    public HardwareStatusV1 status;

    /**
     * Represents the response of memory location from server's Redfish API.
     *
     */
    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MemoryLocationResponse
    {
        public Integer socket;
        public Integer controller;
        public Integer channel;
        public Integer slot;
        
        /**
         * Convert the response to model.
         * @return The model.
         */
        @JsonIgnore
        public MemoryLocation convert() {
            var ret = new MemoryLocation();
            ret.socket = this.socket;
            ret.controller = this.controller;
            ret.channel = this.channel;
            ret.slot = this.slot;
            return ret;
        }
    }
    
    /**
     * Convert the response to model.
     * @return The model.
     */
    @JsonIgnore
    public MemoryResource convert() {
        var ret = new MemoryResource();
//        ret.id = this.id;
//        ret.name = this.name;
//        ret.capacityMiB = this.capacityMiB;
//        ret.manufacturer = this.manufacturer;
//        ret.operatingSpeedMhz = this.operatingSpeedMhz;
//        ret.serialNumber = this.serialNumber;
//        ret.memoryDeviceType = this.memoryDeviceType;
//        ret.dataWidthBits = this.dataWidthBits;
//        ret.rankCount = this.rankCount;
//        ret.partNumber = this.partNumber;
//        ret.deviceLocator = this.deviceLocator;
//        ret.baseModuleType = this.baseModuleType;
//        ret.status = this.status;
//        if (this.memoryLocation != null) {
//            ret.memoryLocation = this.memoryLocation.convert();
//        }        
        return ret;
    }
}
