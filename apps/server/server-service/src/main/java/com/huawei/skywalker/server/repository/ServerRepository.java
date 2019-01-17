package com.huawei.skywalker.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.huawei.skywalker.server.model.Server;

public interface ServerRepository extends MongoRepository<Server, String>
{
    public Server findFirstByAssetInfoSerialNumber(String serialNumber);
}
