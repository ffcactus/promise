package com.promise.apps.server.repository;

import com.promise.apps.server.model.ProcessorResource;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The repository for processor resource.
 */
public interface ProcessorResourceRepository extends MongoRepository<ProcessorResource, String> {

}
