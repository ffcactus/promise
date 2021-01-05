package com.promise.platform.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a single command in JSON patch
 */
@Data
@NoArgsConstructor
public class JsonPatchOperationV1 {
    public String op;
    public String path;
    public String from;
    public Object value;
}
