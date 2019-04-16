package com.promise.study.springtest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.promise.study.springtest.entity.Employee;
import com.promise.study.springtest.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
	
    @Autowired
    private EmployeeService employeeService;
 
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        var result = employeeService.getAllEmployees();
        return result;
    }
}
