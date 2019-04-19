package com.promise.platform.auth;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.promise.platform.auth.model.User;
import com.promise.platform.auth.repository.UserRepository;
import com.promise.platform.sdk.auth.PromiseUserRole;
import com.promise.platform.sdk.dto.auth.CreateUserRequestV1;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private UserRepository userRepository;
	/**
	 * This event is executed as late as conceivably possible to indicate that the
	 * application is ready to service requests.
	 */
	@Override
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		var root = userRepository.findByUsername("root");
		if (!root.isPresent()) {
			CreateUserRequestV1 createRoot = new CreateUserRequestV1(
					"root", 
					"iforgot", 
					"root@promise.com", 
					null,
					Collections.singletonList(PromiseUserRole.PLATFORM_ADMIN.toString()),
					Collections.singletonList("ALL"));			
			userRepository.save(new User(createRoot));
		}
		
		
	}
	
}