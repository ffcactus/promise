package com.promise.platform.auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.promise.platform.auth.model.SessionInfo;

public interface SessionRepository extends MongoRepository<SessionInfo, String> {

}
