package com.promise.platform.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseArgumentV1 {
    private ErrorResponseArgumentType type;
    private String value;
}
