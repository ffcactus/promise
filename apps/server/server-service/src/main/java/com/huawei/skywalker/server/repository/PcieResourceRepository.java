package com.huawei.skywalker.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.huawei.skywalker.server.model.PcieResource;

/**
 * The repository for PCIe resource.
 *
 */
public interface PcieResourceRepository extends MongoRepository<PcieResource, String>
{

}
