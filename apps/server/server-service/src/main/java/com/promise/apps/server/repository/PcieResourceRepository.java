package com.promise.apps.server.repository;

import com.promise.apps.server.model.PcieResource;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The repository for PCIe resource.
 */
public interface PcieResourceRepository extends MongoRepository<PcieResource, String> {

}
