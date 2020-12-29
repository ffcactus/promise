package com.promise.platform.sdk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UpdateErrorMessageRequestV1 {
    public String errorCode;
    public String message;
    public List<String> messageArgs;
}
