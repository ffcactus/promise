package com.promise.collector.plugin.server.huawei.dto;

import com.promise.collector.plugin.server.redfish.dto.OemUtils;
import com.promise.collector.plugin.server.redfish.dto.RedfishStatus;
import com.promise.platform.devicebasic.sdk.dto.device.ProcessorV1;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HuaweiRedfishProcessor {

    @JsonProperty("Name")
    private String location;

    @JsonProperty("Manufacturer")
    private String manufacturer;

    @JsonProperty("Model")
    private String model;

    @JsonProperty("Oem")
    private JsonNode oem;

    @JsonProperty("Status")
    private RedfishStatus status;

    @JsonIgnore
    public ProcessorV1 toResponse() {
        var ret = new ProcessorV1();
        ret.setLocation(location);
        ret.setModel(model);
        ret.setManufacturer(manufacturer);
        ret.setSerialNumber(OemUtils.getOemPropertyAsString(oem, "Huawei", "SerialNumber"));
        ret.setPartNumber(OemUtils.getOemPropertyAsString(oem, "Huawei", "PartNumber"));
        return ret;
    }
}
