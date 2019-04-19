package com.promise.platform.auth.test.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.promise.platform.auth.controller.SessionController;
import com.promise.platform.auth.service.LoginService;
import com.promise.platform.sdk.dto.auth.LoginRequestV1;
import com.promise.platform.sdk.exception.LoginFailureException;
import com.promise.platform.sdk.model.JwtUser;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SessionController.class)
@TestPropertySource(locations = "classpath:application-controller-tests.properties")
public class SessionControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private LoginService loginService;

	private static ObjectMapper mapper;
	private static LoginRequestV1 notExistLoginRequest;
	private static LoginRequestV1 platformManagerLoginRequest;
	private static JwtUser platfromManager;

	static {
		// @formatter:off
		mapper = new ObjectMapper();
		// not existed user.
		notExistLoginRequest = new LoginRequestV1("notExist", "notExist@password");
		// platform administrator.
		platformManagerLoginRequest = new LoginRequestV1("platformManager", "platformManager@password");
		platfromManager = new JwtUser(
				"platformManager",
				"platformManager@token",
				"Promise",
				Collections.singletonList("PlatformAdministrator"), 
				Collections.singletonList("Platform"));
		// @formatter:on		
	}

	@BeforeAll
	public static void beforeAll() {

	}
	
	/**
	 * Test for case that the user not exist.
	 * It should return 401.
	 * @throws Exception
	 */
	@Test
	public void notExistLogin() throws Exception {
		given(loginService.Login(notExistLoginRequest)).willThrow(LoginFailureException.class);
		// @formatter:off
		mvc.perform(
				post(LOGIN_URI)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(notExistLoginRequest)))		
			.andExpect(status().isUnauthorized())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		// @formatter:on		
	}

	/**
	 * Test platform manager login process. It should work.
	 * 
	 * @throws Exception
	 */
	@Test
	public void platformManagerLogin() throws Exception {
		given(loginService.Login(platformManagerLoginRequest)).willReturn(platfromManager);
		// @formatter:off
		mvc.perform(
				post(LOGIN_URI)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(mapper.writeValueAsString(platformManagerLoginRequest)))		
			.andExpect(status().isNoContent())
			.andExpect(header().exists(AUTHORIZATION_HEADER))
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
		// @formatter:on
	}

	
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String LOGIN_URI = "/api/v1/session/login";

}