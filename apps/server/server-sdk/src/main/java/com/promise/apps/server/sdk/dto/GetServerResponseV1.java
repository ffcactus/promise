package com.promise.apps.server.sdk.dto;

import com.promise.platform.common.dto.ScopedResourceResponseV1;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents the GET response of a server.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class GetServerResponseV1 extends ScopedResourceResponseV1 {
    public String description;
    public String type;
    public ServerStateV1 state;
    public ServerHealthV1 health;
    // hardware state and health coming from the hardware only.
    public String hardwareState;
    public String hardwareHealth;
    public String powerState;
    public AssetInfoV1 assetInfo;
    public ProcessorSummaryV1 processorSummary;
    public MemorySummaryV1 memorySummary;
    public StorageSummaryV1 storageSummary;
    public PcieSummaryV1 pcieSummary;
    public AdapterSummaryV1 adapterSummary;
    public PowerSummaryV1 powerSummary;
    public ThermalSummaryV1 thermalSummary;
    public FirmwareSummaryV1 firmwareSummary;
    // references here.
    public String serverHardwareTypeUri;
    public String serverProfileUri;
    public String taskUri;

}
