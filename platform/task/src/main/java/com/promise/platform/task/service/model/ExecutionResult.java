package com.promise.platform.task.service.model;

import com.promise.platform.common.model.ErrorMessage;
import com.promise.platform.task.sdk.dto.ExecutionResultStateV1;
import com.promise.platform.task.sdk.dto.GetExecutionResultResponseV1;
import com.promise.platform.task.sdk.dto.UpdateExecutionResultRequestV1;

public class ExecutionResult {
    public ExecutionResultStateV1 state;
    public ErrorMessage message;

    /**
     * Default constructor.
     */
    public ExecutionResult() {
        this.state = ExecutionResultStateV1.Unknown;
        this.message = new ErrorMessage();
    }

    /**
     * Convert to response DTO.
     *
     * @return response DTO.
     */
    public GetExecutionResultResponseV1 toResponseV1() {
        var ret = new GetExecutionResultResponseV1();
        ret.state = this.state;
        ret.message = this.message.toResponseV1();
        return ret;
    }

    /**
     * Update result according to update result request.
     *
     * @param request
     */
    public void update(UpdateExecutionResultRequestV1 request) {
        if (request.state != null) {
            this.state = request.state;
        }
        if (request.message != null) {
            this.message.update(request.message);
        }
    }
}
