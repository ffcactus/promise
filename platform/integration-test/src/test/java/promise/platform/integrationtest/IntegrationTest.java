package promise.platform.integrationtest;

import org.springframework.beans.factory.annotation.Value;

/**
 * The base class of other integration test.
 *
 */
public class IntegrationTest {
	
	@Value("${integrationTest.host}")
	protected String host;
	@Value("${integrationTest.username}")
	protected String username;
	@Value("${integrationTest.password}")
	protected String password;
}
