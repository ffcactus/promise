package com.promise.platform.devicebasic.sdk.dto.device;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProcessorV1 {
    private String location;
    private String manufacturer;
    private String model;
    private String serialNumber;
    private String partNumber;
}
