package com.promise.apps.server.sdk.dto;

import com.promise.platform.common.dto.BasicResourceResponseV1;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Represents the BIOS settings of the server.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BiosResourceV1 extends BasicResourceResponseV1 {
    public BiosSummaryV1 summary;
    public Map<String, Object> attributes;
}
