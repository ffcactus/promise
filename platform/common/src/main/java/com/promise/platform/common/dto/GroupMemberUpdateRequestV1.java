package com.promise.platform.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * The common request for group member updates.
 */
@Data
@NoArgsConstructor
public class GroupMemberUpdateRequestV1 {
    private Map<Long, GroupMemberOperation> updates;
}
