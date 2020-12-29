package com.promise.apps.server.repository;

import com.promise.apps.server.model.ThermalResource;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The repository for thermal resource.
 */
public interface ThermalResourceRepository extends MongoRepository<ThermalResource, String> {

}
