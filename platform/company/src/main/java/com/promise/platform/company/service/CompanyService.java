package com.promise.platform.company.service;

import com.promise.platform.common.exception.ResourceAlreadyExistException;
import com.promise.platform.company.model.Company;
import com.promise.platform.company.repository.CompanyRepository;
import com.promise.platform.company.sdk.dto.CreateCompanyRequestV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * The service for company.
 */
@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Company createCompany(CreateCompanyRequestV1 request) {
        final var existOne = repository.findFirstByName(request.name);
        if (existOne.isPresent()) {
            throw new ResourceAlreadyExistException();
        }
        final var newOne = new Company(request);
        return repository.save(newOne);
    }

    /**
     * Get companies.
     *
     * @param pageIndex The page index.
     * @param pageSize  The page size.
     * @param direction The order direction.
     * @param orderBy   The property ordered by.
     * @return A page of companies.
     */
    public Page<Company> getCompanies(Integer pageIndex, Integer pageSize, Direction direction, String orderBy) {
        return repository.findAll(PageRequest.of(pageIndex, pageSize, direction, orderBy));
    }

    /**
     * Get the {@link Company} by ID.
     *
     * @param id The ID of the {@link Company}.
     * @return The {@link Company} has this ID.
     */
    public Company getCompanyById(String id) {
        return repository.findById(id).get();
    }

    /**
     * Delete the {@link Company} by ID.
     *
     * @param id The ID of the {@link Company}.
     * @return The {@link Company} that was deleted.
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Company deleteCompanyById(String id) {
        final var ret = repository.findById(id).get();
        repository.deleteById(id);
        return ret;
    }
}
