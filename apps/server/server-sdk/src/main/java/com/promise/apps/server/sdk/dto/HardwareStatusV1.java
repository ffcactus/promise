package com.promise.apps.server.sdk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HardwareStatusV1 {
    public String state;
    public String severity;
    public String health;
}
