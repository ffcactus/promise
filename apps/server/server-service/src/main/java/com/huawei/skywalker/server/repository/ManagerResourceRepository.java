package com.huawei.skywalker.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.huawei.skywalker.server.model.ManagerResource;

/**
 * The repository for manager resource.
 *
 */
public interface ManagerResourceRepository extends MongoRepository<ManagerResource, String>
{

}
