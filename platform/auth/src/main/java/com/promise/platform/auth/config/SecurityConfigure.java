package com.promise.platform.auth.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

//@Configuration
//@ComponentScan(basePackages = { "com.promise.platform.auth", "com.promise.platform.sdk" })
//@ComponentScan(basePackages = {"com.promise.platform.sdk"})
//@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {
//	@Autowired
//	RestAuthenticationEntryPoint entryPoint;
//	@Autowired
//	JwtAuthenticationTokenFilter jwtAuthenticationFilter;

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/configuration/ui",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();
        // @formatter:on
    }


//	@Bean
//	public PermissionEvaluator getAbacPermissionEvaluator() {
//		return new AbacPermissionEvaluator();
//	}
}
