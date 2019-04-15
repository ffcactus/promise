package com.promise.study.controllertest.entity;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Employee {
 
    @Id
    private Long id;
 
    @Size(min = 3, max = 20)
    private String name;
 
}