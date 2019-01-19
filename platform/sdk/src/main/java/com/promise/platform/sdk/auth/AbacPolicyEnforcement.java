package com.promise.platform.sdk.auth;

public interface AbacPolicyEnforcement {
	boolean check(Object subject, Object resource, Object action, Object environment);
}