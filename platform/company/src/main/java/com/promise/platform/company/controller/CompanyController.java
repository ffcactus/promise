package com.promise.platform.company.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.promise.platform.company.model.Company;
import com.promise.platform.company.service.CompanyService;
import com.promise.platform.sdk.dto.CollectionResponseV1;
import com.promise.platform.sdk.dto.company.CreateCompanyRequestV1;
import com.promise.platform.sdk.dto.company.GetCompanyResponseV1;
import com.promise.platform.sdk.dto.company.PatchCompanyRequestV1;

/**
 * The root controller is used to create the company and the first user of the
 * company.
 *
 */
@RestController("/api/v1/platform/companies")
public class CompanyController
{

    @Autowired
    private CompanyService service;

    /**
     * Handle the create {@link Company} request.
     *
     * @param request The {@link CreateCompanyRequestV1}
     * @return The HTTP response that includes the created {@link Company}.
     */
    @PostMapping
    public ResponseEntity<GetCompanyResponseV1> createCompany(@RequestBody CreateCompanyRequestV1 request)
    {
        return new ResponseEntity<>(service.createCompany(request).toResponseV1(), HttpStatus.OK);
    }

    /**
     * Handle the request to list the {@link Company}.
     *
     * @param pageIndex The page index, default is 0.
     * @param pageSize  The page size, default is 20.
     * @param order     The order to list resources.
     * @param orderBy   By which property to list the resources.
     * @return The HTTP response that includes the listed companies.
     */
    @GetMapping
    public ResponseEntity<CollectionResponseV1<GetCompanyResponseV1>> getCompanies(
            @RequestParam(name = "pageIndex", required = false, defaultValue = "0") Integer pageIndex,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") Direction order,
            @RequestParam(name = "orderBy", required = false, defaultValue = "name") String orderBy)
    {
        final var page = service.getCompanies(pageIndex, pageSize, order, orderBy);
        if (page != null)
        {
            final var body = new CollectionResponseV1<GetCompanyResponseV1>();
            body.pageIndex = pageIndex;
            body.pageSize = pageSize;
            body.order = order;
            body.orderBy = orderBy;
            body.total = page.getTotalElements();
            body.hasNext = page.hasNext();
            body.hasPrevious = page.hasPrevious();
            final var baseUri = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest()
                    .getRequestURI();
            body.nextPageUri = !body.hasNext ? null : String
                    .format("%s?pageIndex=%d&pageSize=%d&order=%S&orderBy=%s", baseUri, pageIndex + 1, pageSize, order, orderBy);
            body.previousPageUri = !body.hasPrevious ? null : String
                    .format("%s?pageIndex=%d&pageSize=%d&order=%S&orderBy=%s", baseUri, pageIndex - 1, pageSize, order, orderBy);
            final var members = page.getContent().stream().map(Company::toResponseV1).collect(Collectors.toList());
            body.members = members;
            return new ResponseEntity<>(body, HttpStatus.OK);
        }
        return null;
    }

    /**
     * Handle the request to get the {@link Company} specified by ID.
     *
     * @param id The ID of the {@link Company}.
     * @return The HTTP response that includes the {@link Company}
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<GetCompanyResponseV1> getCompanyById(@PathVariable String id)
    {
        return new ResponseEntity<>(
                service.getCompanyById(id).toResponseV1(),
                HttpStatus.OK);
    }

    /**
     * Handle the request to delete the {@link Company} specified by ID.
     *
     * @param id The ID of the {@link Company}.
     * @return The HTTP response that includes the {@link Company} deleted.
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<GetCompanyResponseV1> deleteCompanyById(@PathVariable String id)
    {
        return new ResponseEntity<>(
                service.deleteCompanyById(id).toResponseV1(),
                HttpStatus.OK);
    }
    
    /**
     * Handle the request to patch the {@link Company} specified by ID.
     * @param id
     * @param request
     * @return
     */
    public ResponseEntity<GetCompanyResponseV1> patchCompanyById(@PathVariable String id, @RequestBody PatchCompanyRequestV1 request) {
    	return null;
    }
}
