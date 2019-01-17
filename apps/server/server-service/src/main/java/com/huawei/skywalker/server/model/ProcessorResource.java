package com.huawei.skywalker.server.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.huawei.skywalker.server.model.Server.ProcessorSummary;
import com.promise.apps.server.sdk.dto.HardwareStatusV1;
import com.promise.apps.server.sdk.dto.ProcessorResourceV1;
import com.promise.apps.server.sdk.dto.ProcessorResourceV1.ProcessorV1;
import com.promise.platform.sdk.model.AssetInfo;
import com.promise.platform.sdk.model.BasicResource;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents the processor resource of a the server.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ProcessorResource extends BasicResource
{
    public ProcessorSummary summary;
    public List<Processor> processors;
    
    public ProcessorResource() {
        summary = new ProcessorSummary();
        processors = new ArrayList<Processor>();
    }

    /**
     * Convert model to response DTO.
     * 
     * @param model The model object.
     * @return The response DTO object.
     */
    public static ProcessorResourceV1 toResponse(ProcessorResource model)
    {
        var ret = new ProcessorResourceV1();
        BasicResource.model2dto(ret, model);
        ret.summary = ProcessorSummary.toResponse(model.summary);
        ret.processors = model.processors.stream().map(Processor::toResponse).collect(Collectors.toList());
        return ret;
    }

    /**
     * Represents the processor on the server.
     *
     */
    @Data
    public static class Processor
    {
        @NotNull
        public AssetInfo assetInfo;
        public String processorType;
        public String processorArchitecture;
        public String instructionSet;
        public Integer maxSpeedMHz;
        public Integer totalCores;
        public Integer totalThreads;
        public Integer socket;
        @NotNull
        public HardwareStatusV1 hardwareStatus;
        
        public Processor() {
            assetInfo = new AssetInfo();
            hardwareStatus = new HardwareStatusV1();
        }

        /**
         * Convert model to response DTO.
         * 
         * @param model The model object.
         * @return The response DTO object.
         */
        public static ProcessorV1 toResponse(Processor model)
        {
            var ret = new ProcessorV1();
            ret.assetInfo = AssetInfo.toResponse(model.assetInfo);
            ret.processorType = model.processorType;
            ret.processorArchitecture = model.processorArchitecture;
            ret.instructionSet = model.instructionSet;
            ret.maxSpeedMHz = model.maxSpeedMHz;
            ret.totalCores = model.totalCores;
            ret.totalThreads = model.totalThreads;
            ret.socket = model.socket;
            ret.hardwareStatus = model.hardwareStatus;
            return ret;
        }
    }
}
