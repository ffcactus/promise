package com.promise.platform.auth.sdk;

public interface AbacPolicyEnforcement {
	boolean check(Object subject, Object resource, Object action, Object environment);
}