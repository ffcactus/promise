package com.promise.platform.common.dto;

import com.promise.platform.common.model.Scope;

import java.util.Set;

/**
 * Represents the GET response to the scope resource.
 */
public class ScopedResourceResponseV1 extends BasicResourceResponseV1 {
    public Set<Scope> scopes;
}
