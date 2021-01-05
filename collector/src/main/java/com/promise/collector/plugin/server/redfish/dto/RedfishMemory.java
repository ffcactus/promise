package com.promise.collector.plugin.server.redfish.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.promise.platform.devicebasic.sdk.dto.device.MemoryV1;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedfishMemory {

    @JsonProperty("DeviceLocator")
    private String location;

    @JsonProperty("Status")
    private RedfishStatus status;

    @JsonProperty("Manufacturer")
    private String manufacturer;

    @JsonProperty("Model")
    private String model;

    @JsonProperty("SerialNumber")
    private String serialNumber;

    @JsonProperty("PartNumber")
    private String partNumber;

    @JsonProperty("CapacityMiB")
    private long capacity;

    public MemoryV1 toResponse() {
        var ret = new MemoryV1();
        ret.setLocation(location);
        ret.setModel(model);
        ret.setManufacturer(manufacturer);
        ret.setSerialNumber(serialNumber);
        ret.setPartNumber(partNumber);
        ret.setCapacity((int) (capacity / 1024L));
        return ret;
    }
}
