package com.promise.platform.task.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.promise.platform.sdk.controller.ExceptionController;
import com.promise.platform.task.sdk.dto.CreateTaskRequestV1;
import com.promise.platform.task.sdk.dto.GetTaskResponseV1;
import com.promise.platform.task.sdk.dto.UpdateTaskRequestV1;
import com.promise.platform.task.sdk.dto.UpdateTaskStepRequestV1;
import com.promise.platform.task.service.exception.TaskStepNotFoundException;
import com.promise.platform.task.service.service.TaskService;

/**
 * The controller for Task service.
 *
 */
@Controller
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
    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_UTF8_VALUE
    }, produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE
    })
    public ResponseEntity<GetTaskResponseV1> createTask(@RequestBody CreateTaskRequestV1 request)
    {
        return new ResponseEntity<GetTaskResponseV1>(
                service.createTask(request).toResponseV1(),
                HttpStatus.OK);
    }

    /**
     * Get a task by it's ID.
     * 
     * @param id The ID of the task.
     * @return The HTTP response with the task.
     */
    @GetMapping(path = "/{id}", produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE
    })
    public ResponseEntity<GetTaskResponseV1> getTaskById(@PathVariable String id)
    {
        return new ResponseEntity<GetTaskResponseV1>(
                service.getTaskById(id).toResponseV1(),
                HttpStatus.OK);
    }

    /**
     * Delete a task by it's ID.
     * 
     * @param id The ID of the task.
     * @return The HTTP response with the deleted task.
     */
    @DeleteMapping(path = "/{id}", produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE
    })
    public ResponseEntity<GetTaskResponseV1> deleteTaskById(@PathVariable String id)
    {
        return new ResponseEntity<GetTaskResponseV1>(
                service.deleteTaskById(id).toResponseV1(),
                HttpStatus.OK);
    }

    /**
     * Update a task by it's ID.
     * 
     * @param id The ID of the task.
     * @return The HTTP response with the task updated.
     */
    @PostMapping(path = "/{id}/actions/update-task", produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE
    })
    public ResponseEntity<GetTaskResponseV1> updateTask(@PathVariable String id, @RequestBody UpdateTaskRequestV1 request)
    {
        return new ResponseEntity<GetTaskResponseV1>(
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
    @PostMapping(path = "/{id}/actions/update-task-step", produces = {
            MediaType.APPLICATION_JSON_UTF8_VALUE
    })
    public ResponseEntity<GetTaskResponseV1> updateTaskStep(@PathVariable String id, @RequestBody UpdateTaskStepRequestV1 request)
            throws TaskStepNotFoundException
    {
        return new ResponseEntity<GetTaskResponseV1>(
                service.updateTaskStep(id, request).toResponseV1(),
                HttpStatus.OK);
    }
}
