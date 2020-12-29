package com.promise.apps.server.repository;

import com.promise.apps.server.model.MemoryResource;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The repository for memory resource.
 */
public interface MemoryResourceRepository extends MongoRepository<MemoryResource, String> {

}
