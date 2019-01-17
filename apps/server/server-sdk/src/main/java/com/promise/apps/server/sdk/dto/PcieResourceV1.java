package com.promise.apps.server.sdk.dto;

import java.util.List;

import com.promise.platform.sdk.dto.BasicResourceResponseV1;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents the PCIe resource response.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class PcieResourceV1 extends BasicResourceResponseV1
{
    public PcieSummaryV1 summary;
    public List<PcieDeviceV1> instances;
    
    /**
     * Represents the PCIe device.
     *
     */
    @Data
    @NoArgsConstructor
    public static class  PcieDeviceV1
    {
    }
}
