package com.huawei.skywalker.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.huawei.skywalker.server.model.MemoryResource;

/**
 * The repository for memory resource.
 *
 */
public interface MemoryResourceRepository extends MongoRepository<MemoryResource, String>
{

}
