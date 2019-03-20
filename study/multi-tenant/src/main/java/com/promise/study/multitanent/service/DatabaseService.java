package com.promise.study.multitanent.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.promise.study.multitanent.domain.CompanyRoot;

/**
 * Provide the capability to manipulate the database for individual companies. 
 *
 */
@Service
public class DatabaseService {
	
	private MongoOperations rootOp;
	
	/**
	 * Default constructor. Since the connection to the root database is fixed, we initialize it here.
	 */
	public DatabaseService() {
		MongoClient client = new MongoClient(
				new ServerAddress("100.101.64.170", 27017), 
				MongoCredential.createCredential("mongo-admin", "admin", "iforgot".toCharArray()), 
				null);
		rootOp = new MongoTemplate(client, "root");
	}
	
	/**
	 * Create the database for a company.
	 * @param companyId The company's ID.
	 * @param username The username of the company's database.
	 * @param password The password of the company's database.
	 */
	public void createDatabase(String companyId, String username, String password) {
		
	}
	
	@Cacheable("database")
	public MongoTemplate getCompanyDatabase(String companyId) {
		CompanyRoot companyRoot = rootOp.findById(companyId, CompanyRoot.class);
		MongoClient client = new MongoClient(
				new ServerAddress("100.101.64.170", 27017), 
				MongoCredential.createCredential(
						companyRoot.getDatabase().getUsername(), 
						"admin", 
						companyRoot.getDatabase().getPassword().toCharArray()), 
				null);
		return new MongoTemplate(client, "company");
		
	}
}
