package com.promise.platform.auth.sdk.policy;


import com.promise.platform.auth.sdk.PolicyRule;

import java.util.List;

public interface PolicyDefinition {
    public List<PolicyRule> getAllPolicyRules();
}