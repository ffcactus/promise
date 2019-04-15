package com.promise.study.controllertest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.promise.study.controllertest.entity.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String>{

}
