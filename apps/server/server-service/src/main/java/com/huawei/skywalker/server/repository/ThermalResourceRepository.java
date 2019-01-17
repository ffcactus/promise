package com.huawei.skywalker.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.huawei.skywalker.server.model.ThermalResource;

/**
 * The repository for thermal resource.
 *
 */
public interface ThermalResourceRepository extends MongoRepository<ThermalResource, String>
{

}
