package com.promise.platform.auth.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
// @ComponentScan(basePackages = { "com.promise.platform.auth", "com.promise.platform.sdk" })
@ComponentScan(basePackages = { "com.promise.platform.sdk" })
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
//	@Autowired
//	RestAuthenticationEntryPoint entryPoint;
//	@Autowired
//	JwtAuthenticationTokenFilter jwtAuthenticationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated();
		// @formatter:on
	}

//	@Bean
//	public PermissionEvaluator getAbacPermissionEvaluator() {
//		return new AbacPermissionEvaluator();
//	}
}
