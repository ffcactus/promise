package com.promise.study.multitanent.service;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

/**
 * Provide the capability to manipulate the database for individual companies.
 *
 */
@Service
public class DatabaseService {
	private static final String ADMIN_DB = "admin";

	private MongoOperations rootOp;

	private MongoClient rootClient;

	private MongoClient companyClient;

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
	public DatabaseService(@Value("${db.root.host}") String rootHost, @Value("${db.root.port}") int rootPort,
			@Value("${db.company.host}") String companyHost, @Value("${db.company.port}") int companyPort) {
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
	public MongoTemplate createCompanyDatabase(String companyId, String username, String password) {

		final var db = companyClient.getDatabase(ADMIN_DB);
		final var databaseName = getCompanyDatabaseName(companyId);
		final var result = db.runCommand(new Document("enablesharding", databaseName));
		System.out.println(result);
		return new MongoTemplate(companyClient, databaseName);
	}

	public MongoTemplate getCompanyDatabase(String companyId) {
		return new MongoTemplate(companyClient, getCompanyDatabaseName(companyId));
	}

	public void removeCompanyDatabase(String companyId) {
		getCompanyDatabase(companyId).getDb().drop();
	}

	private String getCompanyDatabaseName(String companyId) {
		return companyId;
	}
}
