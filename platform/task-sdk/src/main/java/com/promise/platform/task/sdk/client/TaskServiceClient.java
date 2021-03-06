package com.promise.platform.task.sdk.client;

import com.promise.platform.task.sdk.dto.CreateTaskRequestV1;
import com.promise.platform.task.sdk.dto.GetTaskResponseV1;
import com.promise.platform.task.sdk.dto.UpdateTaskRequestV1;
import com.promise.platform.task.sdk.dto.UpdateTaskStepRequestV1;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "promise-platform-task")
@RequestMapping("/api/v1/tasks")
public interface TaskServiceClient {
    /**
     * Create a task.
     *
     * @param request The create task request body.
     * @return The HTTP response with the created task.
     */
    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_UTF8_VALUE
    }, produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE
    })
    public ResponseEntity<GetTaskResponseV1> createTask(@RequestBody CreateTaskRequestV1 request);

    /**
     * Get a task by it's ID.
     *
     * @param id The ID of the task.
     * @return The HTTP response with the task.
     */
    @GetMapping(path = "/{id}", produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE
    })
    public ResponseEntity<GetTaskResponseV1> getTaskById(@PathVariable String id);

    /**
     * Delete a task by it's ID.
     *
     * @param id The ID of the task.
     * @return The HTTP response with the deleted task.
     */
    @DeleteMapping(path = "/{id}", produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE
    })
    public ResponseEntity<GetTaskResponseV1> deleteTaskById(@PathVariable String id);

    /**
     * Update a task by it's ID.
     *
     * @param id The ID of the task.
     * @return The HTTP response with the task updated.
     */
    @PostMapping(path = "/{id}/actions/update-task", produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE
    })
    public ResponseEntity<GetTaskResponseV1> updateTask(@PathVariable String id, @RequestBody UpdateTaskRequestV1 request);

    /**
     * Update a task step by task ID. This will impact on the task.
     *
     * @param id The ID of the task.
     * @return The HTTP response with the task updated.
     */
    @PostMapping(path = "/{id}/actions/update-task-step", produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE
    })
    public ResponseEntity<GetTaskResponseV1> updateTaskStep(@PathVariable String id, @RequestBody UpdateTaskStepRequestV1 request);
}
