package com.huawei.skywalker.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.huawei.skywalker.server.model.PowerResource;

/**
 * The repository for power resource.
 *
 */
public interface PowerResourceRepository extends MongoRepository<PowerResource, String>
{

}
