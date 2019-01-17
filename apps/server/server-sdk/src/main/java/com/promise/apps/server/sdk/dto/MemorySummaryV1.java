package com.promise.apps.server.sdk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The summary of {@link MemoryResourceV1}
 *
 */
@Data
@NoArgsConstructor
public class MemorySummaryV1
{
    public HardwareStatusV1 status;
    public Integer totalSystemMemoryGiB;
}
