package com.promise.apps.server.sdk.dto;

import com.promise.platform.sdk.dto.BasicResourceResponseV1;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents the board resource response.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BoardResourceV1 extends BasicResourceResponseV1 {
    public BoardSummaryV1 summary;
    public List<BoardV1> boards;

    /**
     * Represents the board in {@link BoardResourceV1}
     */
    public static class BoardV1 {

    }
}
