package com.promise.study.springtest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.promise.study.springtest.entity.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String>{

}
