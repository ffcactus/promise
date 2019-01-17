package com.huawei.skywalker.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.huawei.skywalker.server.model.AdapterResource;

/**
 * The repository for adapter resource.
 *
 */
public interface AdapterResourceRepository extends MongoRepository<AdapterResource, String>
{

}
