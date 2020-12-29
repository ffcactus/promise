package com.promise.apps.server.repository;

import com.promise.apps.server.model.StorageResource;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The repository for storage resource.
 */
public interface StorageResourceRepository extends MongoRepository<StorageResource, String> {

}
