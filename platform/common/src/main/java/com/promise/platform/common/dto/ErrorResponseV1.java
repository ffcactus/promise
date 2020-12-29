package com.promise.platform.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseV1 {
    private String errorCode;
    private String reason;
    private List<ErrorResponseArgumentV1> arguments;

    @JsonIgnore
    public String toString() {
        return errorCode;
    }
}
