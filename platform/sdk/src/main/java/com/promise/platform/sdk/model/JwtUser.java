package com.promise.platform.sdk.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class holds the information that will be included in JWT token.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class JwtUser {
	@NotNull
	protected String username;
	@NotNull
	protected String token;
	@NotNull
	protected String company;
	@NotNull
	protected List<String> roles;
	@NotNull
	protected List<String> organizations;
}