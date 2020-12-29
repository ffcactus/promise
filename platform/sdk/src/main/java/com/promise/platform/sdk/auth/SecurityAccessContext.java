package com.promise.platform.sdk.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecurityAccessContext {
    private Object subject;
    private Object resource;
    private Object action;
    private Object environment;
}