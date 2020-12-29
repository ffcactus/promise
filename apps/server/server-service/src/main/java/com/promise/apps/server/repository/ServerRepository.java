package com.promise.apps.server.repository;

import com.promise.apps.server.model.Server;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServerRepository extends MongoRepository<Server, String> {
    public Server findFirstByAssetInfoSerialNumber(String serialNumber);
}
