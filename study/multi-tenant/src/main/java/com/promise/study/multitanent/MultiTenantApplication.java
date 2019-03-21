package com.promise.study.multitanent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import com.promise.study.multitanent.service.DatabaseService;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class MultiTenantApplication implements CommandLineRunner
{
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
    	databaseService.createDatabase("company5", "", "");
    }
}
