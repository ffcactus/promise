package com.promise.platform.company.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.promise.platform.company.model.Company;

public interface CompanyRepository extends MongoRepository<Company, String> {
	public Optional<Company> findFirstByName(String name);
}
