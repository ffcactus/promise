package com.promise.study.multitanent.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

/**
 * Provide the capability to manipulate the database for individual companies.
 *
 */
@Service
public class DatabaseService
{
    private static final String ADMIN_DB = "admin";
    private MongoOperations rootOp;

    private final MongoClient rootClient;

    private final MongoClient companyClient;

    @Value("${db.root.host}")
    private String rootHost;
    @Value("${db.root.port}")
    private int rootPort;
    @Value("${db.company.host}")
    private String companyHost;
    @Value("${db.company.port}")
    private int companyPort;

    /**
     * Default constructor. Since the connection to the root database is fixed, we
     * initialize it here.
     */
    public DatabaseService()
    {
        rootClient = new MongoClient(new ServerAddress(rootHost, rootPort));
        companyClient = new MongoClient(new ServerAddress(companyHost, companyPort));
    }

    /**
     * Create the database for a company.
     *
     * @param companyId The company's ID.
     * @param username  The username of the company's database.
     * @param password  The password of the company's database.
     */
    public void createDatabase(String databaseName, String username, String password)
    {
        final MongoClient client = null;
        try
        {
            final MongoDatabase db = companyClient.getDatabase(ADMIN_DB);
            final Document result = db.runCommand(new Document("enablesharding", databaseName));
            System.out.println(result);
        }
        finally
        {
            if (client != null)
            {
                client.close();
            }
        }
    }

    @Cacheable("database")
    public MongoTemplate getCompanyDatabase(String companyId)
    {
        return new MongoTemplate(companyClient, getCompanyDatabaseName(companyId));
    }

    private String getCompanyDatabaseName(String companyId)
    {
        return companyId;
    }
}
