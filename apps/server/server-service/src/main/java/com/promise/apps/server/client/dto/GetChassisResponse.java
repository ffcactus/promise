package com.promise.apps.server.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.promise.apps.server.sdk.dto.HardwareStatusV1;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response of chassis from server's Redfish API.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetChassisResponse {
    public String id;
    public String name;
    public String chassisType;
    public String manufacturer;
    public String model;
    public String serialNumber;
    public String partNumber;
    public String assertTag;
    public String indicatorLED;
    public HardwareStatusV1 hardwareStatus;
}
