package com.promise.platform.auth.test.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import com.promise.platform.auth.service.SessionService;
import com.promise.platform.sdk.dto.auth.LoginRequestV1;
import com.promise.platform.sdk.exception.UnauthorizedException;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = SessionController.class)
@TestPropertySource(locations = "classpath:application-controller-tests.properties")
public class SessionControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private SessionService loginService;

	private static ObjectMapper mapper;
	private static LoginRequestV1 notExistLoginRequest;
	private static LoginRequestV1 platformManagerLoginRequest;

	static {
		mapper = new ObjectMapper();
		// not existed user.
		notExistLoginRequest = new LoginRequestV1("notExist", "notExist@password");
		// platform administrator.
		platformManagerLoginRequest = new LoginRequestV1("platformManager", "platformManager@password");
	}

	@BeforeAll
	public static void beforeAll() {

	}

	/**
	 * Login process should work and should return 204.
	 * 
	 * @throws Exception
	 */
	@Test
	public void successLogin() throws Exception {
		given(loginService.Login(platformManagerLoginRequest)).willReturn("token");
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
	
	/**
	 * Test for case that the user not exist. It should return 401.
	 * 
	 * @throws Exception
	 */
	@Test
	public void unauthorziedLogin() throws Exception {
		given(loginService.Login(notExistLoginRequest)).willThrow(UnauthorizedException.class);
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
	 * Logout process should work, and should return 200.
	 * @throws Exception
	 */
	@Test
	public void successLogout() throws Exception {
		//given(loginService.Logout("Bearer token")).
		doAnswer((input) -> {
			return null;
		}).when(loginService).Logout(anyString());
		mvc.perform(post(LOGOUT_URI).header(AUTHORIZATION_HEADER, MOCK_TOKEN)).andExpect(status().isOk());
	}
	
	/**
	 * For unauthorized logout, it should return 401
	 * 
	 * @throws Exception
	 */
	@Test
	public void LogoutWithNoToken() throws Exception {
		doThrow(UnauthorizedException.class).when(loginService).Logout(anyString());
		mvc.perform(post(LOGOUT_URI)).andExpect(status().isUnauthorized());
	}
	
	

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String MOCK_TOKEN = "Bearer token";
	private static final String LOGIN_URI = "/api/v1/session/login";
	private static final String LOGOUT_URI = "/api/v1/session/logout";

}
