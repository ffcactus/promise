package com.promise.collector.plugin.server.redfish.dto;

import com.promise.platform.devicebasic.sdk.dto.device.PowerSupplyV1;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedfishPowerSupply {
    @JsonProperty("Name")
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

    @JsonIgnore
    public PowerSupplyV1 toResponse() {
        var ret = new PowerSupplyV1();
        ret.setLocation(location);
        ret.setManufacturer(manufacturer);
        ret.setModel(model);
        ret.setSerialNumber(serialNumber);
        ret.setPartNumber(partNumber);
        return ret;
    }
}
