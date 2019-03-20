package com.promise.study.multitanent.domain;

import lombok.Data;

@Data
public class CompanyRoot {
	private String id;
	private String name;
	private Database database;
	
	@Data
	public static class Database {
		private String username;
		private String password;
	}
}
