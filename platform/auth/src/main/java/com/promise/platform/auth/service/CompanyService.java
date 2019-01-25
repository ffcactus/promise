package com.promise.platform.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.promise.platform.auth.model.Company;
import com.promise.platform.auth.repository.CompanyRepository;
import com.promise.platform.sdk.dto.auth.CreateCompanyRequestV1;
import com.promise.platform.sdk.exception.ResourceAlreadyExistException;

/**
 * The service for company.
 *
 */
@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository repository;
	
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public Company createCompany(CreateCompanyRequestV1 request) {
		var existOne = repository.findFirstByName(request.name);
		if (existOne.isPresent()) {
			throw new ResourceAlreadyExistException();
		}
		var newOne = new Company(request);
		return repository.save(newOne);		
	}
	
	/**
	 * Get companies.
	 * @param pageIndex The page index.
	 * @param pageSize The page size.
	 * @param direction The order direction.
	 * @param orderBy The property ordered by.
	 * @return A page of companies.
	 */
	public Page<Company> getCompany(Integer pageIndex, Integer pageSize, Direction direction, String orderBy) {
		return repository.findAll(PageRequest.of(pageIndex, pageSize, direction, orderBy));
	}
}
