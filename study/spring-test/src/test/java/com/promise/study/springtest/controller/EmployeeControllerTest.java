package com.promise.study.springtest.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.promise.study.springtest.entity.Employee;
import com.promise.study.springtest.service.EmployeeService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTest {
	@Autowired
	private MockMvc mvc;
	@MockBean
	private EmployeeService employeeService;
	
	private final static Employee employeeA;
	static {
		employeeA = new Employee();
		employeeA.setId("id-a");
		employeeA.setName("employee-a");
	}
	
	@Test
	public void login() throws Exception {
		given(this.employeeService.getAllEmployees()).willReturn(Collections.singletonList(employeeA));
		mvc.perform(get(URL))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
	
	private static final String URL = "/api/employees";
}
