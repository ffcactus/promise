package com.huawei.skywalker.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.huawei.skywalker.server.model.FirmwareResource;

/**
 * The repository for firmware resource.
 *
 */
public interface FirmwareResourceRepository extends MongoRepository<FirmwareResource, String>
{

}
