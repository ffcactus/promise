package com.promise.platform.auth.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.promise.platform.auth.controller.UserController;
import com.promise.platform.auth.service.UserService;
import com.promise.platform.sdk.dto.auth.RegisterUserRequestV1;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
@TestPropertySource(locations = "classpath:application-controller-tests.properties")
public class UserControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserService userService;
	
	private static ObjectMapper mapper;
	private static RegisterUserRequestV1 registrationRequest;
	
	static {
		mapper = new ObjectMapper();
		
		registrationRequest = new RegisterUserRequestV1();
		registrationRequest.username = "username";
		registrationRequest.password = "password";
		registrationRequest.email = "email";
	}
	
	/**
	 * Should be able to do registeration.
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 */
	@Test
	public void registration() throws JsonProcessingException, Exception {
		doAnswer((input) -> {
			return null;
		}).when(userService).register(any(RegisterUserRequestV1.class));
		
		// @formatter:off
		mvc.perform(
				post(REGISTRATION_URI)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(registrationRequest)))		
			.andExpect(status().isCreated());
		// @formatter:on
	}
	
	private static final String REGISTRATION_URI = "/api/v1/users";
}
