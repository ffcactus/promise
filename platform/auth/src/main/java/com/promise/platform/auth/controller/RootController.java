package com.promise.platform.auth.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.promise.platform.auth.model.Company;
import com.promise.platform.auth.service.CompanyService;
import com.promise.platform.sdk.dto.CollectionResponseV1;
import com.promise.platform.sdk.dto.auth.CreateCompanyRequestV1;
import com.promise.platform.sdk.dto.auth.GetCompanyResponseV1;

/**
 * The root controller is used to create the company and the first user of the company.
 *
 */
@RestController("/api/v1/auth")
public class RootController {
	
	@Autowired
	private CompanyService service;
	
	@PostMapping("/companies")
	public ResponseEntity<GetCompanyResponseV1> createCompany(@RequestBody CreateCompanyRequestV1 request) {
		return null;
	}
	
	@GetMapping(value = "/companies")
	public ResponseEntity<CollectionResponseV1<GetCompanyResponseV1>> getCompany(
            @RequestParam(name = "pageIndex", required = false, defaultValue = "0") Integer pageIndex,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize,
            @RequestParam(name = "order", required = false, defaultValue = "ASC") Direction order,
            @RequestParam(name = "orderBy", required = false, defaultValue = "name") String orderBy) {		
        var page = service.getCompany(pageIndex, pageSize, order, orderBy);
        if (page != null)
        {
            var body = new CollectionResponseV1<GetCompanyResponseV1>();
            body.pageIndex = pageIndex;
            body.pageSize = pageSize;
            body.order = order;
            body.orderBy = orderBy;
            body.total = page.getTotalElements();
            body.hasNext = page.hasNext();
            body.hasPrevious = page.hasPrevious();
            var baseUri = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRequestURI();
            body.nextPageUri = !body.hasNext ? null : String
                    .format("%s?pageIndex=%d&pageSize=%d&order=%S&orderBy=%s", baseUri, pageIndex + 1, pageSize, order, orderBy);
            body.previousPageUri = !body.hasPrevious ? null : String
                    .format("%s?pageIndex=%d&pageSize=%d&order=%S&orderBy=%s", baseUri, pageIndex - 1, pageSize, order, orderBy);
            var members = page.getContent().stream().map(Company::toResponseV1).collect(Collectors.toList());
            body.members = members;
            return new ResponseEntity<CollectionResponseV1<GetCompanyResponseV1>>(body, HttpStatus.OK);
        }
        return null;
	}
}
