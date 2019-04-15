package com.promise.platform.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.promise.platform.sdk.auth.AbacPermissionEvaluator;
import com.promise.platform.sdk.auth.JwtAuthenticationTokenFilter;
import com.promise.platform.sdk.auth.RestAuthenticationEntryPoint;

@Configuration
@ComponentScan(basePackages = { "com.promise.platform.auth", "com.promise.platform.sdk" })
@EnableWebSecurity
@ConditionalOnProperty(name = "eureka.enabled")
// @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class RootConfigure extends WebSecurityConfigurerAdapter {

	@Autowired
	RestAuthenticationEntryPoint entryPoint;
	@Autowired
	JwtAuthenticationTokenFilter jwtAuthenticationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests().antMatchers("/login/**").permitAll().anyRequest().authenticated();
	}

	@Bean
	public PermissionEvaluator getAbacPermissionEvaluator() {
		return new AbacPermissionEvaluator();
	}
}
