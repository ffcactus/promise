package com.promise.platform.sdk.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateErrorMessageRequestV1
{
    public String errorCode;
    public String message;
    public List<String> messageArgs;
}
