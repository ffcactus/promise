package com.promise.apps.server.sdk.dto;

import com.promise.platform.common.dto.BasicResourceResponseV1;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents the response of processor resource.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ProcessorResourceV1 extends BasicResourceResponseV1 {

    public ProcessorSummaryV1 summary;
    public List<ProcessorV1> processors;

    /**
     * Represents each of the processors.
     */
    public static class ProcessorV1 {
        public AssetInfoV1 assetInfo;
        public String processorType;
        public String processorArchitecture;
        public String instructionSet;
        public Integer maxSpeedMHz;
        public Integer totalCores;
        public Integer totalThreads;
        public Integer socket;
        public HardwareStatusV1 hardwareStatus;
    }
}
