package com.promise.study.springtest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.promise.study.springtest.entity.Employee;
import com.promise.study.springtest.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private EmployeeRepository repository;
	
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}

}
