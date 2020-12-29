package com.promise.platform.sdk.model;

import com.promise.platform.sdk.dto.Scope;

import java.util.Set;

/**
 * The ScopedResource means that the resource is intended to be used in a scope
 * base access control system. A resource may belong to many different scopes.
 */
public class ScopedResource extends BasicResource {
    public Set<Scope> scopes;
}
