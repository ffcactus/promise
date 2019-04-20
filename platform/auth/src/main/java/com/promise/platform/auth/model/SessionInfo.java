package com.promise.platform.auth.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a session created by using username and password.
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionInfo {
	@Id
	private String id;
	private String userId;
	
	
}
