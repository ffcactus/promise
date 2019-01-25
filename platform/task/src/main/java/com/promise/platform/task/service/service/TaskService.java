package com.promise.platform.task.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.promise.platform.sdk.dto.task.CreateTaskRequestV1;
import com.promise.platform.sdk.dto.task.ExecutionResultStateV1;
import com.promise.platform.sdk.dto.task.ExecutionStateV1;
import com.promise.platform.sdk.dto.task.UpdateTaskRequestV1;
import com.promise.platform.sdk.dto.task.UpdateTaskStepRequestV1;
import com.promise.platform.sdk.model.BasicResource;
import com.promise.platform.task.service.TaskApplicationConfig;
import com.promise.platform.task.service.exception.TaskStepNotFoundException;
import com.promise.platform.task.service.model.Task;
import com.promise.platform.task.service.model.TaskStep;
import com.promise.platform.task.service.repository.TaskRepository;

@Service
public class TaskService
{
    @Autowired
    private TaskRepository repository;

    /**
     * Get task by ID.
     * 
     * @param id The task ID.
     * @return The task object if exist.
     */
    public Task getTaskById(String id)
    {
        return repository.findById(id).get();
    }

    /**
     * Create task.
     * 
     * @param request The create task request.
     * @return The created task.
     */
    public Task createTask(CreateTaskRequestV1 request)
    {
        var task = new Task();
        BasicResource.Init(task, TaskApplicationConfig.TaskRootUri, request.name);
        task.messageId = request.messageId;
        task.description = request.description;
        task.state = ExecutionStateV1.Ready;
        task.parentUri = request.parentUri;
        task.createdByUri = request.createdByUri;
        task.targetUri = request.targetUri;
        task.currentStep = request.steps.get(0).name;
        task.percentage = 0;
        task.result.state = ExecutionResultStateV1.Unknown;
        task.expectedDuration = 0;
        for (var stepRequest : request.steps)
        {
            task.expectedDuration += stepRequest.expectedDuration;
            var step = new TaskStep(stepRequest);
            task.steps.add(step);
        }
        return repository.save(task);
    }

    /**
     * Delete task by ID.
     * 
     * @param id The task ID.
     * @return The task deleted.
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Task deleteTaskById(String id)
    {
        var ret = repository.findById(id).get();
        repository.deleteById(id);
        return ret;
    }

    /**
     * Update task by ID.
     * 
     * @param id The task ID.
     * @param request The update task request.
     * @return The updated task.
     */
    public Task updateTask(String id, UpdateTaskRequestV1 request)
    {
        var task = repository.findById(id).get();
        task.update(request);
        return repository.save(task);
    }

    /**
     * Update task step by task ID.
     * 
     * @param id The task ID.
     * @param request The update task step request.
     * @return The updated task.
     * @throws TaskStepNotFoundException When task step not found.
     */
    public Task updateTaskStep(String id, UpdateTaskStepRequestV1 request)
            throws TaskStepNotFoundException
    {
        var task = repository.findById(id).get();
        task.updateStep(request);
        return repository.save(task);
    }
}
