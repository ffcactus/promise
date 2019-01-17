package com.promise.apps.server.sdk.dto;

import java.util.List;

import com.promise.platform.sdk.dto.AssetInfoV1;
import com.promise.platform.sdk.dto.BasicResourceResponseV1;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents the response of storage resource.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ThermalResourceV1 extends BasicResourceResponseV1
{
    public ThermalSummaryV1 summary;
    public List<FanV1> fans;
    
    /**
     * Represents the fan in {@link ThermalResourceV1}.
     *
     */
    @Data
    @NoArgsConstructor
    public static class FanV1
    {
        public String index;
        public AssetInfoV1 assetInfo;
        public HardwareStatusV1 hardwareStatus;
    }

    
}
