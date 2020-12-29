package com.promise.platform.devicebasic.sdk.dto.device;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class DiscoverDeviceRequestV1 {
    private String name;
    private String description;
    private String address;
    private String username;
    private String password;
    private String type;
    private String vendor;
    private Long collectorGroupId;

    @Override
    public String toString() {
        return "DiscoverDeviceRequestV1{" +
                "address='" + address + '\'' +
                '}';
    }
}
