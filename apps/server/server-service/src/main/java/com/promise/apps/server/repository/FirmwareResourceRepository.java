package com.promise.apps.server.repository;

import com.promise.apps.server.model.FirmwareResource;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The repository for firmware resource.
 */
public interface FirmwareResourceRepository extends MongoRepository<FirmwareResource, String> {

}
