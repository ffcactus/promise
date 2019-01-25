package com.promise.platform.sdk.dto.auth;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "password" })
public class CreateUserRequestV1 {
	public String username;
	public String password;
	public String email;
	public String company;
	public List<String> roles;
	public List<String> organizations;
}
