package com.promise.apps.server.sdk.dto;

import java.util.List;

import com.promise.platform.sdk.dto.AssetInfoV1;
import com.promise.platform.sdk.dto.BasicResourceResponseV1;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents the response of memory resource.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MemoryResourceV1 extends BasicResourceResponseV1
{
    public MemorySummaryV1 summary;
    public List<MemoryV1> instances;

    public static class MemoryV1
    {
        public String id;
        public String name;
        public AssetInfoV1 assetInfo;
        public Integer capacityMiB;
        public Integer operatingSpeedMhz;
        public String memoryDeviceType;
        public Integer dataWidthBits;
        public Integer rankCount;
        public String deviceLocator;
        public String baseModuleType;
        public MemoryLocationV1 memoryLocation;
        public HardwareStatusV1 status;

        /**
         * Represents the memory location on the board.
         *
         */
        @Data
        @NoArgsConstructor
        public static class MemoryLocationV1
        {
            public Integer socket;
            public Integer controller;
            public Integer channel;
            public Integer slot;
        }
    }
}
