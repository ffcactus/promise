package com.promise.apps.server.client.dto;

import com.promise.apps.server.model.Server;
import com.promise.apps.server.sdk.dto.HardwareStatusV1;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents the response of ComputerSystem from server's Redfish API.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetComputerSystemResponse {
    public String originUri;
    public String assetTag;
    public String manufacturer;
    public String model;
    public String serialNumber;
    public String uuid;
    public String hostName;
    public String partNumber;
    public List<String> HostingRoles;
    public HardwareStatusV1 status;
    public String powerState;
    public BootResponse boot;
    public List<TrustedModuleResponse> trustedModules;
    public String biosVersion;
    public ProcessorSummaryResponse processorSummary;
    public MemorySummaryResponse memorySummary;

    /**
     * Represents the response of boot from server's Redfish API.
     */
    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BootResponse {
        public String bootSourceOverrideTarget;
        public String bootSourceOverrideEnabled;
        public String bootSourceOverrideMode;
    }

    /**
     * Represents the response of trusted module from server's Redfish API.
     */
    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TrustedModuleResponse {
        public String firmwareVersion;
        public String firmwareVersion2;
        public String interfaceType;
        public String interfaceTypeSelection;
    }

    /**
     * Represents the response of processor summary from server's Redfish API.
     */
    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ProcessorSummaryResponse {
        public Integer count;
        public String model;
        public HardwareStatusV1 status;

        /**
         * Convert the response to model.
         *
         * @return The model.
         */
        @JsonIgnore
        public Server.ProcessorSummary convert() {
            var ret = new Server.ProcessorSummary();
            ret.count = this.count;
            ret.model = this.model;
            ret.status = this.status;
            return ret;
        }
    }

    /**
     * Represents the response of memory summary from server's Redfish API.
     */
    @Data
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MemorySummaryResponse {
        public HardwareStatusV1 status;
        public Integer totalSystemMemoryGiB;

        /**
         * Convert the response to model.
         *
         * @return The model.
         */
        @JsonIgnore
        public Server.MemorySummary convert() {
            var ret = new Server.MemorySummary();
            ret.totalSystemMemoryGiB = this.totalSystemMemoryGiB;
            ret.status = this.status;
            return ret;
        }
    }
}
