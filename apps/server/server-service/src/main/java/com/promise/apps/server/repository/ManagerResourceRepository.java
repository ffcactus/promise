package com.promise.apps.server.repository;

import com.promise.apps.server.model.ManagerResource;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The repository for manager resource.
 */
public interface ManagerResourceRepository extends MongoRepository<ManagerResource, String> {

}
