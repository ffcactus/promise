package com.promise.platform.company.repository;

import com.promise.platform.company.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CompanyRepository extends MongoRepository<Company, String> {
    public Optional<Company> findFirstByName(String name);
}
