package com.promise.platform.sdk.auth.policy;

import com.promise.platform.sdk.auth.PolicyRule;

import java.util.List;

public interface PolicyDefinition {
    public List<PolicyRule> getAllPolicyRules();
}