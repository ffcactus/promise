package com.promise.apps.server.repository;

import com.promise.apps.server.model.BiosResource;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The repository for BIOS resource.
 */
public interface BiosResourceRepository extends MongoRepository<BiosResource, String> {

}
