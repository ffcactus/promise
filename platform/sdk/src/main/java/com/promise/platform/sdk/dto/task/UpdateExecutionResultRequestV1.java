package com.promise.platform.sdk.dto.task;

import com.promise.platform.sdk.dto.UpdateErrorMessageRequestV1;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateExecutionResultRequestV1 {
    public ExecutionResultStateV1 state;
    public UpdateErrorMessageRequestV1 message;
}
