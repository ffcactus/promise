package com.promise.study.multitanent.service;

import org.bson.Document;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import com.promise.study.multitanent.domain.CompanyRoot;

/**
 * Provide the capability to manipulate the database for individual companies. 
 *
 */
@Service
public class DatabaseService {
	private static final String ADMIN_DB = "admin";
	private MongoOperations rootOp;
	
	/**
	 * Default constructor. Since the connection to the root database is fixed, we initialize it here.
	 */
	public DatabaseService() {
//		MongoClient client = new MongoClient(new ServerAddress("100.101.64.170", 27019));
//		rootOp = new MongoTemplate(client, "root");
	}
	
	/**
	 * Create the database for a company.
	 * @param companyId The company's ID.
	 * @param username The username of the company's database.
	 * @param password The password of the company's database.
	 */
	public void createDatabase(String databaseName, String username, String password) {
		MongoClient client = null;
		try {
			client = new MongoClient(new ServerAddress("100.101.64.170", 27019));
			MongoDatabase db = client.getDatabase(ADMIN_DB);
			Document result = db.runCommand(new Document("enablesharding", databaseName));
			System.out.println(result);
		} finally {
			if (client != null ) {
				client.close();
			}
		}		
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
