package com.promise.apps.server.sdk.dto;

import java.util.List;

import com.promise.platform.sdk.dto.BasicResourceResponseV1;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents the firmware resource of the server.
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class FirmwareResourceV1 extends BasicResourceResponseV1
{
    public FirmwareSummaryV1 summay;
    public List<FirmwareV1> firmwares;
    
    /**
     * Represents the firmware in {@link FirmwareResourceV1}
     *
     */
    @Data
    @NoArgsConstructor
    public static class FirmwareV1 {
        public String id;
        public String name;
        public String version;
        public Boolean updateable;  
    }
}
