package com.promise.platform.sdk.auth.policy;

import java.util.List;

import com.promise.platform.sdk.auth.PolicyRule;

public interface PolicyDefinition {
	public List<PolicyRule> getAllPolicyRules();
}