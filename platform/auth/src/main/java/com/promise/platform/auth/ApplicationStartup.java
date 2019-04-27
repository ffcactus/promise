package com.promise.platform.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.promise.platform.auth.model.User;
import com.promise.platform.auth.repository.UserRepository;
import com.promise.platform.sdk.dto.auth.RegisterUserRequestV1;

@Component
public class ApplicationStartup /*implements ApplicationListener<ApplicationReadyEvent> */{

//	@Autowired
//	private UserRepository userRepository;
//	/**
//	 * This event is executed as late as conceivably possible to indicate that the
//	 * application is ready to service requests.
//	 */
//	@Override
//	@Transactional(isolation=Isolation.SERIALIZABLE)
//	public void onApplicationEvent(final ApplicationReadyEvent event) {
//		var root = userRepository.findByUsername("root");
//		if (!root.isPresent()) {
//			RegisterUserRequestV1 createRoot = new RegisterUserRequestV1(
//					"root", 
//					"iforgot", 
//					"root@promise.com");			
//			userRepository.save(new User(createRoot));
//		}				
//	}	
}