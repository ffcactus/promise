package com.promise.platform.task.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.promise.platform.sdk.controller.ExceptionController;
import com.promise.platform.sdk.dto.task.CreateTaskRequestV1;
import com.promise.platform.sdk.dto.task.GetTaskResponseV1;
import com.promise.platform.sdk.dto.task.UpdateTaskRequestV1;
import com.promise.platform.sdk.dto.task.UpdateTaskStepRequestV1;
import com.promise.platform.task.service.exception.TaskStepNotFoundException;
import com.promise.platform.task.service.service.TaskService;

/**
 * The controller for Task service.
 *
 */
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController extends ExceptionController
{
    @Autowired
    TaskService service;

    /**
     * Create a task.
     *
     * @param request The create task request body.
     * @return The HTTP response with the created task.
     */
    @PostMapping
    @HystrixCommand(fallbackMethod = "createTaskFallback")
    public ResponseEntity<GetTaskResponseV1> createTask(@RequestBody CreateTaskRequestV1 request)
    {
        return new ResponseEntity<>(
                service.createTask(request).toResponseV1(),
                HttpStatus.OK);
    }

    public String createTaskFallback()
    {
        return "createTaskFallback().";
    }

    /**
     * Get a task by it's ID.
     *
     * @param id The ID of the task.
     * @return The HTTP response with the task.
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<GetTaskResponseV1> getTaskById(@PathVariable String id)
    {
        return new ResponseEntity<>(
                service.getTaskById(id).toResponseV1(),
                HttpStatus.OK);
    }

    /**
     * Delete a task by it's ID.
     *
     * @param id The ID of the task.
     * @return The HTTP response with the deleted task.
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<GetTaskResponseV1> deleteTaskById(@PathVariable String id)
    {
        return new ResponseEntity<>(
                service.deleteTaskById(id).toResponseV1(),
                HttpStatus.OK);
    }

    /**
     * Update a task by it's ID.
     *
     * @param id The ID of the task.
     * @return The HTTP response with the task updated.
     */
    @PostMapping(path = "/{id}/actions/update-task")
    public ResponseEntity<GetTaskResponseV1> updateTask(@PathVariable String id, @RequestBody UpdateTaskRequestV1 request)
    {
        return new ResponseEntity<>(
                service.updateTask(id, request).toResponseV1(),
                HttpStatus.OK);
    }

    /**
     * Update a task step by task ID. This will impact on the task.
     *
     * @param id The ID of the task.
     * @return The HTTP response with the task updated.
     * @throws TaskStepNotFoundException When task step not found.
     */
    @PostMapping(path = "/{id}/actions/update-task-step")
    public ResponseEntity<GetTaskResponseV1> updateTaskStep(@PathVariable String id, @RequestBody UpdateTaskStepRequestV1 request)
            throws TaskStepNotFoundException
    {
        return new ResponseEntity<>(
                service.updateTaskStep(id, request).toResponseV1(),
                HttpStatus.OK);
    }
}
