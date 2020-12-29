package com.promise.collector.plugin.server.redfish.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedfishSystem {
    @JsonProperty("Status")
    private RedfishStatus status;

    @JsonProperty("PowerState")
    private String powerState;
}
