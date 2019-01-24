package promise.platform.integrationtest.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import com.promise.platform.sdk.dto.auth.LoginRequestV1;
import com.promise.platform.sdk.dto.auth.LoginResponseV1;

import promise.platform.integrationtest.IntegrationTest;

/**
 * The integration test for auth module.
 *
 */
public class AuthTest extends IntegrationTest
{
	private static RestTemplate restTemplate;
	
	@BeforeAll
	public static void beforeAll() {
		restTemplate = new RestTemplate();
	}
	
	@Test
	public void testLogin() {
		var loginRequest = new LoginRequestV1();
		loginRequest.username = username;
		loginRequest.password = password;
		
		var httpRequest = new HttpEntity<>(loginRequest);
		var httpResponse = restTemplate.postForEntity(host + "/api/v1/auth/login", httpRequest, LoginResponseV1.class);
		
		assertEquals(HttpStatus.OK, httpResponse.getStatusCode());
	}
}
