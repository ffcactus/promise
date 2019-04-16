package com.promise.platform.sdk.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * PromiseUserDetails contains the informations that required for authentication
 * and authorization.
 *
 */
public class PromiseUserDetails extends JwtUser implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3994452351352130007L;
	@Getter
	@Setter
	@JsonIgnore
	protected String password;

	public PromiseUserDetails(String username, String company, List<String> roles, List<String> organizations, String password) {
		super(username, null, company, roles, organizations);
		this.password = password;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}

	public List<GrantedAuthority> getAuthorities() {
		return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
}