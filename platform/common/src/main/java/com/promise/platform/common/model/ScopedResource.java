package com.promise.platform.common.model;

import com.promise.platform.common.model.Scope;

import java.util.Set;

/**
 * The ScopedResource means that the resource is intended to be used in a scope
 * base access control system. A resource may belong to many different scopes.
 */
public class ScopedResource extends BasicResource {
    public Set<Scope> scopes;
}
