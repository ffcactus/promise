package com.promise.apps.server.client.dto;

import com.promise.apps.server.model.ProcessorResource;
import com.promise.apps.server.sdk.dto.HardwareStatusV1;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response of processor from server's Redfish API.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetProcessorResponse {
    public String id;
    public String name;
    public String processorType;
    public String processorArchitecture;
    public String instructionSet;
    public String manufacturer;
    public String model;
    public Integer maxSpeedMHz;
    public Integer totalCores;
    public Integer totalThreads;
    public Integer socket;
    public HardwareStatusV1 status;
    public ProcessorOem oem;

    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ProcessorOem {
        public ProcessorHuawei huawei;

        @Data
        @NoArgsConstructor
        @JsonIgnoreProperties(ignoreUnknown = true)
        private static class ProcessorHuawei {
            public String partNumber;
            public String serialNumber;
        }
    }

    /**
     * Convert the response to model.
     *
     * @return The model.
     */
    @JsonIgnore
    public ProcessorResource.Processor convert() {
        var ret = new ProcessorResource.Processor();
        ret.assetInfo.manufacturer = this.manufacturer;
        ret.assetInfo.model = this.model;
        ret.assetInfo.partNumber = this.oem.huawei.partNumber;
        ret.assetInfo.serialNumber = this.oem.huawei.serialNumber;
        ret.hardwareStatus = this.status;

        ret.processorType = this.processorType;
        ret.processorArchitecture = this.processorArchitecture;
        ret.instructionSet = this.instructionSet;
        ret.maxSpeedMHz = this.maxSpeedMHz;
        ret.totalCores = this.totalCores;
        ret.totalThreads = this.totalThreads;
        ret.socket = this.socket;
        return ret;
    }
}
