package com.promise.platform.sdk.auth.manager;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.stereotype.Component;

import com.promise.platform.sdk.auth.provider.JwtAuthenticationProvider;

@Component
public class JwtAuthenticationManager extends ProviderManager {
	@Autowired
	public JwtAuthenticationManager(JwtAuthenticationProvider provider) {
		super(Collections.singletonList(provider));
	}
}