package com.huawei.skywalker.server.model;

import java.util.List;
import java.util.stream.Collectors;

import com.huawei.skywalker.server.model.Server.PowerSummary;
import com.promise.apps.server.sdk.dto.HardwareStatusV1;
import com.promise.apps.server.sdk.dto.PowerResourceV1;
import com.promise.apps.server.sdk.dto.PowerResourceV1.PowerSupplyV1;
import com.promise.platform.sdk.model.AssetInfo;
import com.promise.platform.sdk.model.BasicResource;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents the power resource of a server.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PowerResource extends BasicResource
{
    public PowerSummary summary;
    public List<PowerSupply> powerSupplies;
    
    /**
     * Convert model to response DTO.
     * 
     * @param model The model object.
     * @return The response DTO object.
     */
    public static PowerResourceV1 toResponse(PowerResource model) {
        var ret = new PowerResourceV1();
        ret.summary = PowerSummary.toResponse(model.summary);
        ret.powerSupplies = model.powerSupplies.stream().map(PowerSupply::toResponse).collect(Collectors.toList());
        return ret;
    }
    
    /**
     * Represents the power supply in {@link PowerResource}
     *
     */
    @Data
    @NoArgsConstructor
    public static class PowerSupply
    {
        public String memberId;
        public String name;
        public AssetInfo assetInfo;
        public HardwareStatusV1 status;
        public String powerSupplyType;
        public Integer powerCapacityWatts;
        public String firmwareVersion;
        
        /**
         * Convert model to response DTO.
         * 
         * @param model The model object.
         * @return The response DTO object.
         */
        public static PowerSupplyV1 toResponse(PowerSupply model) {
            var ret = new PowerSupplyV1();
            ret.memberId = model.memberId;
            ret.name = model.name;
            ret.assetInfo = AssetInfo.toResponse(model.assetInfo);
            ret.status = model.status;
            ret.powerSupplyType = model.powerSupplyType;
            ret.powerCapacityWatts = model.powerCapacityWatts;
            ret.firmwareVersion = model.firmwareVersion;
            return ret;
        }
    }
}
