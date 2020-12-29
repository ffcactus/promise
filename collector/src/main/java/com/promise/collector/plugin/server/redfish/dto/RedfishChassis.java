package com.promise.collector.plugin.server.redfish.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedfishChassis {

    @JsonProperty("Id")
    private String id;

    @JsonProperty("ChassisType")
    private String chassisType;

    @JsonProperty("Manufacturer")
    private String manufacturer;

    @JsonProperty("Model")
    private String Model;

    @JsonProperty("SerialNumber")
    private String serialNumber;

    @JsonProperty("PartNumber")
    private String partNumber;

    @JsonProperty("AssetTag")
    private String assetTag;

    @JsonProperty("Status")
    private RedfishStatus redfishStatus;

    @JsonProperty("IndicatorLED")
    private String indicatorLED;

    @JsonProperty("Links")
    private Map<String, List<OdataId>> links;
}
