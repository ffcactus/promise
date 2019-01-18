package com.promise.platform.task.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.promise.platform.task.service.model.Task;

/**
 * The task repository.
 *
 */
public interface TaskRepository extends MongoRepository<Task, String>
{

}
