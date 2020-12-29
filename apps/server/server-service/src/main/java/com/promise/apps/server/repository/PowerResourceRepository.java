package com.promise.apps.server.repository;

import com.promise.apps.server.model.PowerResource;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The repository for power resource.
 */
public interface PowerResourceRepository extends MongoRepository<PowerResource, String> {

}
