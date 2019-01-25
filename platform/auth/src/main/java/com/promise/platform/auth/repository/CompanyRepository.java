package com.promise.platform.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.promise.platform.auth.model.Company;

public interface CompanyRepository extends MongoRepository<Company, String> {
	public Optional<Company> findFirstByName(String name);
}
