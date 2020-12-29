package com.promise.collector.plugin.server.redfish.dto;

import com.promise.platform.devicebasic.sdk.dto.device.FanV1;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedfishFan {
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

    public FanV1 toResponse() {
        var ret = new FanV1();
        ret.setLocation(location);
        ret.setManufacturer(manufacturer);
        ret.setModel(model);
        ret.setPartNumber(partNumber);
        return ret;
    }
}
