package com.promise.platform.task.service.repository;

import com.promise.platform.task.service.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The task repository.
 */
public interface TaskRepository extends MongoRepository<Task, String> {

}
