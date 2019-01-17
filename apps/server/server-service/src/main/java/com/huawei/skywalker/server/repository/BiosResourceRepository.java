package com.huawei.skywalker.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.huawei.skywalker.server.model.BiosResource;

/**
 * The repository for BIOS resource.
 *
 */
public interface BiosResourceRepository extends MongoRepository<BiosResource, String>
{

}
