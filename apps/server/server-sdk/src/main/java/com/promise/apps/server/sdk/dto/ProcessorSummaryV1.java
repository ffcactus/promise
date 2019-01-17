package com.promise.apps.server.sdk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The summary of {@link ProcessorResourceV1}
 *
 */
@Data
@NoArgsConstructor
public class ProcessorSummaryV1
{
    public Integer count;
    public String model;
    public HardwareStatusV1 status;
}