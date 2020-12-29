package com.promise.platform.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MappingListItemV1 {
    private Long id;
    private Long leftId;
    private Long rightId;
}
