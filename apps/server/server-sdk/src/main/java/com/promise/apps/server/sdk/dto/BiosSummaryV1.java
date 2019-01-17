package com.promise.apps.server.sdk.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The summary of {@link BiosResourceV1}
 *
 */
@Data
@NoArgsConstructor
public class BiosSummaryV1 {
    public List<String> bootOrder;
}