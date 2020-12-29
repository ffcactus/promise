package com.promise.apps.server.sdk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The summary of {@link BiosResourceV1}
 */
@Data
@NoArgsConstructor
public class BiosSummaryV1 {
    public List<String> bootOrder;
}