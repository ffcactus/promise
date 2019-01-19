package com.promise.platform.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.promise.platform.auth.sdk.AbacPermissionEvaluator;
import com.promise.platform.auth.sdk.JwtAuthenticationTokenFilter;
import com.promise.platform.auth.sdk.RestAuthenticationEntryPoint;

@Configuration
@ComponentScan(basePackages = { "com.promise.aa", "com.promise.common" })
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class RootConfigure extends WebSecurityConfigurerAdapter {

	@Autowired
	RestAuthenticationEntryPoint entryPoint;
	@Autowired
	JwtAuthenticationTokenFilter jwtAuthenticationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(entryPoint)
//                .and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/login/**").permitAll().anyRequest().authenticated();
//                .and()
//                .authorizeRequests().antMatchers("/rest/v1/login").permitAll()

//        http
//                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Bean
	public PermissionEvaluator getAbacPermissionEvaluator() {
		return new AbacPermissionEvaluator();
	}
}
