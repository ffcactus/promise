package com.promise.apps.server.repository;

import com.promise.apps.server.model.AdapterResource;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The repository for adapter resource.
 */
public interface AdapterResourceRepository extends MongoRepository<AdapterResource, String> {

}
