package com.huawei.skywalker.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.huawei.skywalker.server.model.StorageResource;

/**
 * The repository for storage resource.
 *
 */
public interface StorageResourceRepository extends MongoRepository<StorageResource, String>
{

}
