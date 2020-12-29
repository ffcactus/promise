package com.promise.platform.sdk.dto;

import java.util.Set;

/**
 * Represents the GET response to the
 * {@link com.huawei.skywalker.com.model.SocpeResource}
 */
public class ScopedResourceResponseV1 extends BasicResourceResponseV1 {
    public Set<Scope> scopes;
}
