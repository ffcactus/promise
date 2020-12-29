package com.promise.platform.sdk.auth.manager;

import com.promise.platform.sdk.auth.provider.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class JwtAuthenticationManager extends ProviderManager {

    @Autowired
    public JwtAuthenticationManager(JwtAuthenticationProvider provider) {
        super(Collections.singletonList(provider));
    }
}