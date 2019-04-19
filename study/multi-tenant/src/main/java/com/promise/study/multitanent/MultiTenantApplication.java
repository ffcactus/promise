package com.promise.study.multitanent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import com.promise.study.multitanent.domain.Server;
import com.promise.study.multitanent.service.DatabaseService;

import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Criteria.where;

@SpringBootApplication(exclude = {
        MongoAutoConfiguration.class, MongoDataAutoConfiguration.class
})
public class MultiTenantApplication implements CommandLineRunner
{
	private static final int NUM_OF_COMPANY = 100;
	private static final int SERVER_NUM_PER_COMPANY = 100;
	private static final int FETCH_COUNT = 10000;
	
    @Autowired
    private DatabaseService databaseService;

    public static void main(String[] args)
    {
        SpringApplication.run(MultiTenantApplication.class, args);
    }

    @Override
    public void run(String... args)
            throws Exception
    {
    	List<String> companyNames = new ArrayList<String>();
    	for (int i = 0; i < NUM_OF_COMPANY; i++) {
    		companyNames.add("company" + i);
    	}
    	
    	// reset.
    	for(String name : companyNames) {
    		databaseService.removeCompanyDatabase(name);
    	}
//    	
//    	for(String name : companyNames) {
//    		var mongoTemplate = databaseService.createCompanyDatabase(name, "", "");
//    		mongoTemplate.insertAll(createCompanyServers(name));    		
//    	}
//    	 
//    	// start test.
//    	var start = System.currentTimeMillis();
//    	for (int i = 0; i < FETCH_COUNT; i++) {
//    		
//    		var r = new Random();
//    		var companyName = "company" + r.nextInt(NUM_OF_COMPANY);
//    		var serverName = "server" + r.nextInt(SERVER_NUM_PER_COMPANY);
//    		var mongoTemplate = databaseService.getCompanyDatabase(companyName);
//    		var result = mongoTemplate.find(query(where("name").is(serverName)), Server.class);
//    		if (result.size() != 1) {
//    			System.out.printf("Failed to fetch %s in %s\n", serverName, companyName);
//    		} else {
//    			System.out.printf("Success on %s %s %d\n", serverName, companyName, i);
//    		}
//    	}
//    	var end = System.currentTimeMillis();
//    	System.out.printf("Total time = %s", (end - start) / 1000.0f);
    	
    }
    
    private Collection<Server> createCompanyServers(String companyName) {
    	var ret = new ArrayList<Server>();
    	for (int i = 0; i < SERVER_NUM_PER_COMPANY; i++) {
    		var server = new Server();
    		server.setId(UUID.randomUUID().toString());
    		server.setName("server" + i);
    		server.setDescription(companyName + " own this server.");
    		ret.add(server);
    	}
    	return ret;
    }
}
