package com.promise.platform.auth.test.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.promise.platform.auth.controller.SessionController;
import com.promise.platform.auth.service.LoginService;
import com.promise.platform.auth.service.UserService;
import com.promise.platform.sdk.dto.auth.LoginRequestV1;
import com.promise.platform.sdk.model.JwtUser;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SessionController.class)
@TestPropertySource(locations="classpath:application-controller-tests.properties")	
public class SessionControllerTest
{
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private LoginService loginService;
	
	
	@Test
	public void login() throws Exception {
		given(this.loginService.Login(new LoginRequestV1())).willReturn(
				new JwtUser("Username", "Company", Collections.singletonList("Role"), Collections.singletonList("Organization")));
		mvc.perform(post(LOGIN_URI))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
	}
	
	private static final String LOGIN_URI = "/api/v1/session/login";
	
}
