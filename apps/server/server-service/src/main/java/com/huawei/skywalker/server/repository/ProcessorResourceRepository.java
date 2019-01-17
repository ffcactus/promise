package com.huawei.skywalker.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.huawei.skywalker.server.model.ProcessorResource;

/**
 * The repository for processor resource.
 *
 */
public interface ProcessorResourceRepository extends MongoRepository<ProcessorResource, String>
{

}
