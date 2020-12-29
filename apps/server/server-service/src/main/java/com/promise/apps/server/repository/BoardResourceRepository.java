package com.promise.apps.server.repository;

import com.promise.apps.server.model.BoardResource;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The repository for board resource.
 */
public interface BoardResourceRepository extends MongoRepository<BoardResource, String> {

}
