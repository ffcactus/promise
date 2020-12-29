package com.promise.collector.plugin.server.redfish.dto;

import com.promise.platform.devicebasic.sdk.dto.device.DriveV1;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedfishDrive {
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

    @JsonProperty("CapacityBytes")
    private long capacity;

    public DriveV1 toResponse() {
        var drive = new DriveV1();
        drive.setLocation(location);
        drive.setModel(model);
        drive.setManufacturer(manufacturer);
        drive.setSerialNumber(serialNumber);
        drive.setPartNumber(partNumber);
        drive.setCapacity((int) (capacity / (1024L * 1024L * 1024L)));
        return drive;
    }
}
