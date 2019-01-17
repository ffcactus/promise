package com.huawei.skywalker.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.huawei.skywalker.server.model.BoardResource;

/**
 * The repository for board resource.
 *
 */
public interface BoardResourceRepository extends MongoRepository<BoardResource, String>
{

}
