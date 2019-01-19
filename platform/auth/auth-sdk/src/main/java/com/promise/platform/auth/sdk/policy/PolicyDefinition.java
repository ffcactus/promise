package com.promise.platform.auth.sdk.policy;

import java.util.List;

import com.promise.platform.auth.sdk.PolicyRule;

public interface PolicyDefinition {
	public List<PolicyRule> getAllPolicyRules();
}