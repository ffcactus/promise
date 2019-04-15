package com.promise.study.controllertest.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.promise.study.controllertest.entity.Employee;
import com.promise.study.controllertest.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private EmployeeRepository repository;
	
	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}

}
