package com.promise.apps.server.model;

import com.promise.apps.server.sdk.dto.HardwareStatusV1;
import com.promise.apps.server.sdk.dto.MemoryResourceV1;
import com.promise.apps.server.sdk.dto.MemoryResourceV1.MemoryV1;
import com.promise.apps.server.sdk.dto.MemoryResourceV1.MemoryV1.MemoryLocationV1;
import com.promise.platform.common.model.BasicResource;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the memory resource of server.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class MemoryResource extends BasicResource {
    public Server.MemorySummary summary;
    public List<Memory> instances;

    /**
     * Convert model to response DTO.
     *
     * @param model The model object.
     * @return The response DTO object.
     */
    public static MemoryResourceV1 toResponse(MemoryResource model) {
        var ret = new MemoryResourceV1();
        BasicResource.model2dto(ret, model);
        ret.summary = Server.MemorySummary.toResponse(model.summary);
        ret.instances = model.instances.stream().map(Memory::toResponse).collect(Collectors.toList());
        return ret;
    }

    public static class Memory {
        public String id;
        public String name;
        public AssetInfo assetInfo;
        public Integer capacityMiB;
        public Integer operatingSpeedMhz;
        public String memoryDeviceType;
        public Integer dataWidthBits;
        public Integer rankCount;
        public String deviceLocator;
        public String baseModuleType;
        public MemoryLocation memoryLocation;
        public HardwareStatusV1 status;

        /**
         * Convert model to response DTO.
         *
         * @param model The model object.
         * @return The response DTO object.
         */
        public static MemoryV1 toResponse(Memory model) {
            var ret = new MemoryV1();
            ret.id = model.id;
            ret.name = model.name;
            ret.assetInfo = AssetInfo.toResponse(model.assetInfo);
            ret.capacityMiB = model.capacityMiB;
            ret.operatingSpeedMhz = model.operatingSpeedMhz;
            ret.memoryDeviceType = model.memoryDeviceType;
            ret.dataWidthBits = model.dataWidthBits;
            ret.rankCount = model.rankCount;
            ret.deviceLocator = model.deviceLocator;
            ret.baseModuleType = model.baseModuleType;
            ret.memoryLocation = MemoryLocation.toResponse(model.memoryLocation);
            ret.status = model.status;
            return ret;
        }

        /**
         * Represents the location of the memory.
         */
        @Data
        @NoArgsConstructor
        public static class MemoryLocation {
            public Integer socket;
            public Integer controller;
            public Integer channel;
            public Integer slot;

            /**
             * Convert model to response DTO.
             *
             * @param model The model object.
             * @return The response DTO object.
             */
            public static MemoryLocationV1 toResponse(MemoryLocation model) {
                var ret = new MemoryLocationV1();
                ret.socket = model.socket;
                ret.controller = model.controller;
                ret.channel = model.channel;
                ret.slot = model.slot;
                return ret;
            }
        }
    }
}
