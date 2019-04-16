package com.promise.platform.auth.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "eureka.enabled")
// @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class CloudConfigure {
}
