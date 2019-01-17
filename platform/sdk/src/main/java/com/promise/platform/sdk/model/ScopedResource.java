package com.promise.platform.sdk.model;

import java.util.Set;

import com.promise.platform.sdk.dto.Scope;

/**
 * The ScopedResource means that the resource is intended to be used in a scope
 * base access control system. A resource may belong to many different scopes.
 *
 */
public class ScopedResource extends BasicResource
{
    public Set<Scope> scopes;
}
