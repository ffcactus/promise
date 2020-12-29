package com.promise.apps.server.sdk.dto;

import com.promise.platform.sdk.dto.AssetInfoV1;
import com.promise.platform.sdk.dto.BasicResourceResponseV1;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents the response of power resource.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PowerResourceV1 extends BasicResourceResponseV1 {
    public PowerSummaryV1 summary;
    public List<PowerSupplyV1> powerSupplies;

    /**
     * Represents the power supply in {@link PowerResourceV1}
     */
    @Data
    @NoArgsConstructor
    public static class PowerSupplyV1 {
        public String memberId;
        public String name;
        public AssetInfoV1 assetInfo;
        public HardwareStatusV1 status;
        public String powerSupplyType;
        public Integer powerCapacityWatts;
        public String firmwareVersion;
    }
}
