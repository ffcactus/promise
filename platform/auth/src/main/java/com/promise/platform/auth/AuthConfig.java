package com.promise.platform.auth;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http)
            throws Exception
    {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers("/rest/v1/auth/login").permitAll()
                .anyRequest().authenticated();
    }
}