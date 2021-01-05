package com.promise.platform.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * The common response for group member updates.
 */
@Data
@NoArgsConstructor
public class GroupMemberUpdateResponseV1 {

    public GroupMemberUpdateResponseV1(Map<Long, GroupMemberUpdateResult> results) {
        this.results = results;
    }

    private Map<Long, GroupMemberUpdateResult> results;
}
